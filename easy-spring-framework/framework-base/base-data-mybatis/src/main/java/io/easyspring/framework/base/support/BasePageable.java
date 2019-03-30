package io.easyspring.framework.base.support;

import io.easyspring.framework.base.properties.BasePageProperties;
import io.easyspring.framework.base.utils.SortBuilder;
import io.easyspring.framework.common.exception.CommonException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

/**
 * 自定义的分页信息
 *
 * @author summer
 * DateTime 2019-01-10 15:15
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

    /**
     * 封装自定义的分页信息
     *
     * @param basePageProperties 系统配置的分页数据
     * @param pageable spring 的 pageable 对象
     * @return io.easyspring.framework.base.support.BasePageable
     * Author summer
     * Version V1.0.0-RELEASE
     * DateTime 2019-03-30 20:21
     */
    public static BasePageable of(BasePageProperties basePageProperties, Pageable pageable) {
        // 如果分页信息为空, 则返回默认的分页信息
        if (pageable == null) {
            return new BasePageable(basePageProperties.getDefaultPageNumber(),
                    basePageProperties.getDefaultPageSize(),
                    null);
        }

        /*
         * 获取分页信息, 并重新初始化值
         */
        // 获取每页显示数量
        int pageSize = pageable.getPageSize();
        pageSize = pageSize <= 0 ? basePageProperties.getDefaultPageSize() : pageSize;
        // 获取页码
        int pageNumber = pageable.getPageNumber();
        pageNumber = pageNumber <= 0 ? basePageProperties.getDefaultPageNumber() : pageNumber;
        // 获取传入的排序数据
        String orderBy = SortBuilder.build(pageable.getSort());

        return new BasePageable(pageNumber, pageSize, orderBy);
    }

    /**
     * 获取分页的起始下标
     *
     * @return int
     * Author summer
     * Version V1.0.0-RELEASE
     * DateTime 2019-03-30 20:43
     */
    public int getStartIndex(){
        // 页码
        int pageNumber = this.pageNumber;
        // 每页显示的数量
        int pageSize = this.pageSize;

        // 参数校验
        if (pageSize <= 0) {
            throw new CommonException("获取分页查询的起始下标出错");
        }

        // 如果页码是第一页(或更小), 则起始下标为 0
        if (pageNumber <= 1) {
            return 0;
        }

        // 如果分页的下标大于 1
        return (pageNumber - 1) * pageSize;
    }


    /**
     * 获取分页的结束下标
     *
     * @return int
     * Author summer
     * Version V1.0.0-RELEASE
     * DateTime 2019-03-30 20:43
     */
    public int getLastIndex() {
        // 页码
        int pageNumber = this.pageNumber;
        // 每页显示的数量
        int pageSize = this.pageSize;

        // 参数校验
        if (pageSize <= 0) {
            throw new CommonException("获取分页查询的结束下标出错");
        }

        // 如果页码是第一页
        if (pageNumber <= 1) {
            return pageSize;
        }

        // 返回页码也每页显示数量的乘积
        return pageNumber * pageSize;
    }
}
