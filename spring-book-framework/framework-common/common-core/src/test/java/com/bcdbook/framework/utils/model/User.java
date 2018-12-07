package com.bcdbook.framework.utils.model;

import java.util.Date;

/**
 * 用于测试的 user 实体
 *
 * @author summer
 * @date 2018-12-07 22:01
 * @version V1.0.0-RELEASE
 */
public class User extends BaseModel {
    private String userName;
    private Integer age;
    private Date birthday;

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
