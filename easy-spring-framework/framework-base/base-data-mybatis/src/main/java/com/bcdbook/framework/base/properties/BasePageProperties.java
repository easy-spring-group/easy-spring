package com.bcdbook.framework.base.properties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 分页的配置类
 *
 * @author summer
 * @date 2018-12-03 20:02
 * @version V1.0.0-RELEASE
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ConfigurationProperties(prefix = "book.framework.base.page")
@Component
public class BasePageProperties {

    /**
     * 默认每页显示的数量
     */
    private static final int DEFAULT_PAGE_SIZE = 10;
    /**
     * 默认的页码
     */
    private static final int DEFAULT_PAGE_NUMBER = 1;
    /**
     * 每页最大的显示数量
     */
    private static final int MAX_PAGE_SIZE = 1000;

    /**
     * 默认每页显示的数量
     */
    private Integer defaultPageSize;
    /**
     * 每页显示的最大数量
     */
    private Integer maxPageSize;
    /**
     * 默认的页码
     */
    private Integer defaultPageNumber;

    public Integer getDefaultPageSize() {
        if (defaultPageSize == null || defaultPageSize <= 0) {
            defaultPageSize = DEFAULT_PAGE_SIZE;
        }
        return defaultPageSize;
    }

    public void setDefaultPageSize(Integer defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }

    public Integer getMaxPageSize() {
        if (maxPageSize == null || maxPageSize <= 0) {
            maxPageSize = MAX_PAGE_SIZE;
        }
        return maxPageSize;
    }

    public void setMaxPageSize(Integer maxPageSize) {
        this.maxPageSize = maxPageSize;
    }

    public Integer getDefaultPageNumber() {
        if (defaultPageNumber == null || defaultPageNumber <= 0) {
            defaultPageNumber = DEFAULT_PAGE_NUMBER;
        }
        return defaultPageNumber;
    }

    public void setDefaultPageNumber(Integer defaultPageNumber) {
        this.defaultPageNumber = defaultPageNumber;
    }
}
