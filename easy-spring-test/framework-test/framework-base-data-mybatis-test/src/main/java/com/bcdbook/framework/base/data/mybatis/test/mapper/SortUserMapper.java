package com.bcdbook.framework.base.data.mybatis.test.mapper;

import com.bcdbook.framework.base.data.mybatis.test.model.SortUser;
import com.bcdbook.framework.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户对象的 mapper
 *
 * @author summer
 * @date 2018-12-03 00:35
 * @version V1.0.0-RELEASE
 */
@Mapper
public interface SortUserMapper extends BaseMapper<SortUser> {
}