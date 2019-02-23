package io.easyspring.framework.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义的分页信息
 *
 * @author summer
 * @date 2019-01-10 15:15
 * @version V1.0.0-RELEASE
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasePageable {

    /**
     * 页码
     */
    private Integer pageNumber;
    /**
     * 每页显示数量
     */
    private Integer pageSize;
    /**
     * 排序信息
     */
    private String order;
}
