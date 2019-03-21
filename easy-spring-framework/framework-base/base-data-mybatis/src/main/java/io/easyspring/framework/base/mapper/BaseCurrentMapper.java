package io.easyspring.framework.base.mapper;

import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;
import java.util.Map;

/**
 * 通用的底层 mapper, 直接执行传入的 sql
 *
 * @author summer
 * @version V1.0.0-RELEASE
 * DateTime 2018-12-05 22:20
 */
@RegisterMapper
public interface BaseCurrentMapper {

    /**
     * 使用 sql 语句进行插入操作
     *
     * Author summer
     * DateTime 2018-12-06 16:45
     * @param sql 想要执行的 sql
     * @return int
     * Version V1.0.0-RELEASE
     */
    @Insert("${sql}")
    int insertUseSql(@Param("sql") String sql);

    /**
     * 根据传入的 sql 执行删除操作, 并返回影响的数据条数
     *
     * Author summer
     * DateTime 2018-12-06 16:50
     * @param sql 将要执行的 sql 语句
     * @return int
     * Version V1.0.0-RELEASE
     */
    @Delete("${sql}")
    int deleteUseSql(@Param("sql") String sql);

    /**
     * 根据传入的 sql 执行更新操作并返回影响的数据条数
     *
     * Author summer
     * DateTime 2018-12-06 16:53
     * @param sql 想要执行的 sql
     * @return int
     * Version V1.0.0-RELEASE
     */
    @Update("${sql}")
    int updateUseSql(@Param("sql") String sql);

    /**
     * 根据传入的 sql 查询出 map 对象
     * 注意: 此方法的动态性很强, 使用时需要严格控制
     *
     * Author summer
     * DateTime 2018-12-05 22:28
     * @param sql 想要执行的 sql
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * Version V1.0.0-RELEASE
     */
    @Select("${sql}")
    Map<String, Object> selectMapUseSql(@Param("sql") String sql);

    /**
     * 根据传入的 sql 查询出对应的数据集合
     *
     * Author summer
     * DateTime 2018-12-06 17:05
     * @param sql 将要执行的 sql
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * Version V1.0.0-RELEASE
     */
    @Select("${sql}")
    List<Map<String, Object>> selectListUseSql(@Param("sql") String sql);
}
