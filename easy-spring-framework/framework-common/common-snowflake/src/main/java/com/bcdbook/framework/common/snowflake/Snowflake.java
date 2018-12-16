package com.bcdbook.framework.common.snowflake;

import lombok.extern.slf4j.Slf4j;

/**
 * 基于Twitter的Snowflake算法实现分布式高效有序ID生产
 *
 * 结构: SnowFlake的结构如下(每部分用-分开)
 * 结构序列: 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * 序列概要: 1位标识, 41位时间截, 10位的数据机器位, 12位序列
 * 序列详解:
 *   1位标识: 由于 long 基本类型在 Java 中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0
 *   41位时间截: 毫秒级，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)，
 *     这里的的开始时间截，一般是我们的 id 生成器开始使用的时间，由我们程序来指定的（如下下面程序 Snowflake 类的 START_TIMESTAMP 属性
 *     41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69
 *   10位的数据机器位: 可以部署在1024个节点，包括5位 dataCenterId 和5位 workerId
 *   12位序列: 毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号
 *   加起来刚好64位，为一个Long型。
 * 优点: SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，
 *   经测试，SnowFlake每秒能够产生26万ID左右。
 *
 * @author summer
 * @date 2018-12-03 12:12
 * @version V1.0.0-RELEASE
 */
@Slf4j
public class Snowflake {

    /** 开始时间戳 2018-12-01 */
    private static final long START_TIMESTAMP = 1543593600000L;
    /** 机器 id 所占的位数 */
    private static final long WORKER_ID_BITS = 5L;
    /** 数据中心 id 所占的位数 */
    private static final long DATA_CENTER_ID_BITS = 5L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     * -1L ^ (-1L << workerIdBits)
     */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    /**
     * 支持的最大数据标识id，结果是31
     * -1L ^ (-1L << dataCenterIdBits)
     */
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);

    /** 序列在id中占的位数 */
    private static final long SEQUENCE_BITS = 12L;

    /** 机器ID向左移12位 */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    /** 数据标识id向左移17位(12+5) */
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    /** 时间截向左移22位(5+5+12) */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;
    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     * -1L ^ (-1L << sequenceBits)
     */
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /** 工作机器ID(0~31) */
    private long workerId;
    /** 数据中心ID(0~31) */
    private long dataCenterId;
    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;
    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    /**
     * 全参构造方法
     *
     * @author summer
     * @date 2018-12-02 23:40
     * @param workerId 工作ID (0~31)
     * @param dataCenterId 数据中心ID (0~31)
     * @version V1.0.0-RELEASE
     */
    private Snowflake(long workerId, long dataCenterId) {
        log.info("[id 生成器] id 生成器初始化, 机器 id: {}, 数据中心 id: {}", workerId, dataCenterId);
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenter Id can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 定义 snowflake 对象, 用于单例返回
     */
    private static Snowflake instance = null;

    /**
     * 饿汉模式, 修改生成 Snowflake 对象
     *
     * @author summer
     * @date 2018-12-03 12:31
     * @param workerId 机器码
     * @param dataCenterId 数据中心
     * @return com.bcdbook.framework.common.snowflake.Snowflake
     * @version V1.0.0-RELEASE
     */
    static Snowflake getInstance(long workerId, long dataCenterId){
        if (instance == null){
            synchronized (Snowflake.class){
                if (instance == null) {
                    instance = new Snowflake(workerId, dataCenterId);
                }
            }
        }
        return instance;
    }

    /**
     * 生成全局唯一的有序 id
     *
     * @author summer
     * @date 2018-12-03 12:32
     * @return long
     * @version V1.0.0-RELEASE
     */
    synchronized long nextId() {
        long timestamp = timeGen();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        // 如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp == timestamp) {
            //sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            sequence = (sequence + 1) & SEQUENCE_MASK;
            //判断是否溢出,也就是每毫秒内超过4095，当为4095时，与sequenceMask相与，sequence就等于0
            if (sequence == 0) {
                //自旋等待到下一毫秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - START_TIMESTAMP) << TIMESTAMP_LEFT_SHIFT)
                | (dataCenterId << DATA_CENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }

    /**
     * @author summer
     * @date 2017/12/29 下午1:12
     * @param lastTimestamp 上次使用的时间戳
     * @return long
     * @description 防止产生的时间比之前的时间还要小（由于NTP回拨等问题）,保持增量的趋势.
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * @author summer
     * @date 2017/12/29 下午1:12
     * @return long
     * @description 获取当前的时间戳
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }
}