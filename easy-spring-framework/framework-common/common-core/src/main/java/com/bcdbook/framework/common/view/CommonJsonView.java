package com.bcdbook.framework.common.view;

/**
 * 定义 json 返回视图的类
 *
 * @author summer
 * @date 2019-01-11 16:04
 * @version V1.0.0-RELEASE
 */
public interface CommonJsonView {

    /**
     * 返回 json 数据时的视图 -- 简单视图
     *
     * @author summer
     * @date 2019-01-11 16:06
     * @version V1.0.0-RELEASE
     */
    interface SimpleView {}

    /**
     * 返回 json 数据时的视图 -- 详情视图
     *
     * @author summer
     * @date 2019-01-11 16:06
     * @version V1.0.0-RELEASE
     */
    interface DetailView extends SimpleView {}

    /**
     * 返回 json 数据时的视图 -- 私密视图
     *
     * @author summer
     * @date 2019-01-11 16:06
     * @version V1.0.0-RELEASE
     */
    interface SecretView extends DetailView {}


}
