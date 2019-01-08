package com.bcdbook.framework.base.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 *
 * @author summer
 * @date 2018-12-02 16:12
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode
@Accessors(chain = true)
public class BaseModel implements Serializable {

    private static final long serialVersionUID = -6751591121677001166L;

    /**
     * 声明简单视图
     */
    public interface SimpleView {}
    /**
     * 声明详细视图
     */
    public interface DetailView extends SimpleView {}
    /**
     * 声明私密视图
     */
    public interface SecretView extends DetailView {}

    /**
     * 删除标识 -- 已删除
     */
    public static final Boolean DELETED_TRUE = true;
    /**
     * 删除标识 -- 未删除
     */
    public static final Boolean DELETED_FALSE = false;

    /**
     * id 主键
     */
    @Id
    @JsonView(SimpleView.class)
    private Long id;
    /**
     * 创建时间
     */
    @JsonView(SimpleView.class)
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonView(SimpleView.class)
    private Date updateTime;
    /**
     * 伪删除标记字段
     * true: 已删除
     * false: 未删除
     */
    @JsonView(DetailView.class)
    private Boolean deleted = DELETED_FALSE;
}
