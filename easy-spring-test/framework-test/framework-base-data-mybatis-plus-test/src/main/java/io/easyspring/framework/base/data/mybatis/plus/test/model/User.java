package io.easyspring.framework.base.data.mybatis.plus.test.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户实体对象
 *
 * @author summer
 * @date 2018-12-04 22:09
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("tab_user")
public class User{

    private Long id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户编码
     */
    private String userCode;
}
