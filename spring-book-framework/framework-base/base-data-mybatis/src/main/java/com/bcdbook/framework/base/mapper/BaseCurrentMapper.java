package com.bcdbook.framework.base.mapper;

import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;
import java.util.Map;

/**
 * 通用的底层 mapper, 直接执行传入的 sql
 *
 * @author summer
 * @date 2018-12-05 22:20
 * @version V1.0.0-RELEASE
 */
@RegisterMapper
public interface BaseCurrentMapper {

    /**
     * 根据传入的 sql 查询出 map 对象
     *
     * @author summer
     * @date 2018-12-05 22:28
     * @param sql 想要执行的 sql
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @version V1.0.0-RELEASE
     */
    @Select("${sql}")
    Map<String, Object> selectMap(@Param("sql") String sql);

    @Select("${sql}")
    List<Map<String, Object>> selectMapList(@Param("sql") String sql);

    @Insert("${sql}")
    int insert(@Param("sql") String sql);

    @Update("${sql}")
    int update(@Param("sql") String sql);

    @Delete("${sql}")
    int delete(@Param("sql") String sql);
}
