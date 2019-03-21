package io.easyspring.framework.common.snowflake;

import io.easyspring.framework.common.snowflake.properties.SnowflakeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 全局唯一 id 生成器的工具类
 *
 * @author summer
 * DateTime 2018-12-03 00:42
 * @version V1.0.0-RELEASE
 */
@Component
public class SnowflakeHelp {

    /**
     * 注入配置参数
     */
    @Autowired
    private SnowflakeProperties snowflakeProperties;

    /** 默认的机器 id */
    private static final long DEFAULT_WORKER_ID = 0L;
    /** 默认的数据中心 id */
    private static final long DEFAULT_DATA_CENTER_ID = 0L;
    /**
     * 获取全局唯一 id
     *
     * Author summer
     * DateTime 2018-12-03 12:05
     * @return long
     * Version V1.0.0-RELEASE
     */
    public long nextId(){
        return instanceSnowflake().nextId();
    }

    /**
     * 获取全局唯一 id 生成器
     *
     * Author summer
     * DateTime 2018-12-02 23:43
     * @return io.easyspring.framework.base.utils.uuid.Snowflake
     * Version V1.0.0-RELEASE
     */
    private Snowflake instanceSnowflake(){
        /*
         * 获取机器 id
         */
        Long workerId = snowflakeProperties.getWorkerId();
        workerId = (workerId == null || workerId < 0) ? DEFAULT_WORKER_ID : workerId;
        /*
         * 获取数据中心 id
         */
        Long dataCenterId = snowflakeProperties.getDataCenterId();
        dataCenterId = (dataCenterId == null || dataCenterId < 0) ? DEFAULT_DATA_CENTER_ID : dataCenterId;

        // 生成 id 生成器
        Snowflake snowflake = Snowflake.getInstance(workerId, dataCenterId);

        return snowflake;
    }
}
