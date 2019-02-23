package io.easyspring.framework.common.utils.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 定义测试实体的父类
 *
 * @author summer
 * @date 2018-12-07 21:59
 * @version V1.0.0-RELEASE
 */
public class BaseModel implements Serializable {
    /**
     * 更新时间
     */
    private Date updateTime;

    public BaseModel() {
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
