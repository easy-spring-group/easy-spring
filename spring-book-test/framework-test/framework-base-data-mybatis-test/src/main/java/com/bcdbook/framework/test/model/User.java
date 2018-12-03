package com.bcdbook.framework.test.model;

import com.bcdbook.framework.base.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Table;

@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Table(name = "tab_user")
public class User extends BaseModel {

    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户编码
     */
    private String userCode;
}
