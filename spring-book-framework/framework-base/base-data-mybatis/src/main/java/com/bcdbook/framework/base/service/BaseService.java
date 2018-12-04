package com.bcdbook.framework.base.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BaseService<T> {

    /**
     * 在执行插入之前由我们自己处理 id 的生成
     *
     * @author summer
     * @date 2018-12-02 22:44
     * @param entity 需要保存的实体对象
     * @return void
     * @version V1.0.0-RELEASE
     */
    void perInsert(T entity);


    /**
     * 用于保存数据的方法, 如果 id 为空则执行添加操作, 否则执行修改操作
     *
     * @author summer
     * @date 2018-12-02 23:48
     * @param entity 需要保存的数据对象
     * @return T
     * @version V1.0.0-RELEASE
     */
    T insertOrUpdateSelective(T entity);

    /**
     * 用于新建(插入)数据的方法
     *
     * @author summer
     * @date 2018-12-02 23:49
     * @param entity 需要保存的数据对象
     * @return T
     * @version V1.0.0-RELEASE
     */
    T insertAll(T entity);

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @author summer
     * @date 2018-12-02 23:50
     * @param entity 需要保存的实体
     * @return T
     * @version V1.0.0-RELEASE
     */
    T insertSelective(T entity);

    /**
     * 根据数据的 id 删除(伪删除)数据的方法
     *
     * @author summer
     * @date 2018-12-02 23:50
     * @param id 将要删除(伪删除) 的数据的 id
     * @return int
     * @version V1.0.0-RELEASE
     */
    int delete(Long id);

    /**
     * 根据数据的条件对象删除(伪删除)数据的方法
     *
     * @author summer
     * @date 2018-12-02 23:50
     * @param entity 将要删除(伪删除) 的数据的 条件对象
     * @return int
     * @version V1.0.0-RELEASE
     */
    int deleteSelective(T entity);

    /**
     * 根据对象的主键, 删除对应的数据对象
     *
     * @author summer
     * @date 2018-12-02 23:51
     * @param id 将要删除的数据的 id
     * @return int
     * @version V1.0.0-RELEASE
     */
    int realDelete(Long id);

    /**
     * 根据实体条件删除对应的数据
     *
     * @author summer
     * @date 2018-12-02 23:51
     * @param entity 筛选条件(使用等号过滤)
     * @return int
     * @version V1.0.0-RELEASE
     */
    int realDeleteSelective(T entity);

    /**
     * 用于更新数据的方法
     *
     * @author summer
     * @date 2018-12-02 23:52
     * @param entity 需要保存的数据对象
     * @return T
     * @version V1.0.0-RELEASE
     */
    T updateAll(T entity);

    /**
     * 根据传入的数据对象, 进行动态的拼接修改语句,如果有值则进行修改,否则直接忽略此字段
     *
     * @author summer
     * @date 2018-12-02 23:52
     * @param entity 需要修改的数据对象
     * @return T
     * @version V1.0.0-RELEASE
     */
    T updateSelective(T entity);

    /**
     * 根据 id 查询对应的对象
     *
     * @author summer
     * @date 2018-12-02 23:54
     * @param id 数据的 id
     * @return T
     * @version V1.0.0-RELEASE
     */
    T get(Long id);


    /**
     * 根据传入的实体对象条件, 查询出符合条件的数据
     * 注意: 使用此方法获取唯一对象有风险
     *
     * @author summer
     * @date 2018-12-02 23:54
     * @param entity 用于封装查询条件的实体
     * @return T
     * @version V1.0.0-RELEASE
     */
    T getByParameters(T entity);

    /**
     * 完全根据查询条件进行查询, 不会自动排查被标记成已删除的记录
     *
     * @author summer
     * @date 2018-12-02 23:54
     * @param entity 查询条件
     * @return T
     * @version V1.0.0-RELEASE
     */
    T getAllByParameters(T entity);

    /**
     * 根据传入的实体对象条件, 查询出符合条件的数据的集合
     *
     * @author summer
     * @date 2018-12-02 23:55
     * @param entity 用于封装查询条件的实体
     * @return java.util.List<T>
     * @version V1.0.0-RELEASE
     */
    List<T> list(T entity);

    /**
     * 完全根据查询条件进行查询, 不会自动排查被标记成已删除的记录
     *
     * @author summer
     * @date 2018-12-02 23:55
     * @param entity 查询条件
     * @return java.util.List<T>
     * @version V1.0.0-RELEASE
     */
    List<T> listAll(T entity);

    /**
     * 根据传入的实体条件 / 页码 / 每页显示的数据量
     * 查询出符合条件的分页对象
     *
     * @author summer
     * @date 2018-12-03 20:27
     * @param entity 用于封装条件的实体类
     * @return com.github.pagehelper.Page<T>
     * @version V1.0.0-RELEASE
     */
    Page<T> findPage(T entity);

    /**
     * 根据传入的查询条件查询出所有满足条件的信息, 并进行分页,
     * 不会自动过滤被标记成已删除的数据
     *
     * @author summer
     * @date 2018-12-03 20:28
     * @param entity 用于封装条件的实体类
     * @return com.github.pagehelper.Page<T>
     * @version V1.0.0-RELEASE
     */
    Page<T> findPageAll(T entity);

    /**
     * 根据传入的实体条件 / 页码 / 每页显示的数据量
     * 查询出符合条件的分页对象
     *
     * @author summer
     * @date 2018-12-03 20:58
     * @param entity 用于封装条件的实体类
     * @param orderBy 分页条件 例如: "update_time desc" 这里的 orderBy 并不会自动转换大小写
     * @return com.github.pagehelper.Page<T>
     * @version V1.0.0-RELEASE
     */
    Page<T> findPage(T entity, String orderBy);

    /**
     * 完全根据查询条件查询符合条件的分页信息, 不会自动过滤被标记成删除的字段
     *
     * @author summer
     * @date 2018-12-03 20:58
     * @param entity 用于封装条件的实体类
     * @param orderBy 分页条件 例如: "update_time desc" 这里的 orderBy 并不会自动转换大小写
     * @return com.github.pagehelper.Page<T>
     * @version V1.0.0-RELEASE
     */
    Page<T> findPageAll(T entity, String orderBy);

    /**
     * 根据传入的实体条件 / 页码 / 每页显示的数据量
     * 查询出符合条件的分页对象
     *
     * @author summer
     * @date 2018-12-03 21:07
     * @param entity 用于封装条件的实体类
     * @param pageable 分页条件 例如: "update_time desc" 这里的 orderBy 并不会自动转换大小写
     * @return com.github.pagehelper.Page<T>
     * @version V1.0.0-RELEASE
     */
    Page<T> findPage(T entity, Pageable pageable);

    /**
     * 完全根据查询条件查询出符合条件的分页信息, 不会过滤掉被标记成已删除的数据
     *
     * @author summer
     * @date 2018-12-03 22:16
     * @param entity 实体条件
     * @param pageable 分页信息
     * @return com.github.pagehelper.Page<T>
     * @version V1.0.0-RELEASE
     */
    Page<T> findPageAll(T entity, Pageable pageable);

    /**
     * 根据传入的实体条件 / 页码 / 每页显示的数据量
     * 查询出符合条件的分页对象 (包括分页信息的详情)
     *
     * @author summer
     * @date 2018-12-03 22:18
     * @param entity 用于封装条件的实体类
     * @return com.github.pagehelper.PageInfo<T>
     * @version V1.0.0-RELEASE
     */
    PageInfo<T> findPageInfo(T entity);

    /**
     * 根据传入的实体条件 / 页码 / 每页显示的数据量
     * 查询出符合条件的分页对象 (包括分页信息详情)
     *
     * @author summer
     * @date 2018-12-03 22:19
     * @param entity 用于封装条件的实体类
     * @param orderBy 分页条件 例如: "update_time desc" 这里的 orderBy 并不会自动转换大小写
     * @return com.github.pagehelper.PageInfo<T>
     * @version V1.0.0-RELEASE
     */
    PageInfo<T> findPageInfo(T entity, String orderBy);

    /**
     * 根据传入的实体条件 / 页码 / 每页显示的数据量
     * 查询出符合条件的分页对象
     *
     * @author summer
     * @date 2017/12/29 下午3:43
     * @param entity 用于封装条件的实体类
     * @param pageable 分页条件 例如: "update_time desc" 这里的 orderBy 并不会自动转换大小写
     * @return com.github.pagehelper.Page<T>
     */
    PageInfo<T> findPageInfo(T entity, Pageable pageable);

    /**
     * 根据传入的查询条件, 查询出符合条件的数据的数量
     *
     * @author summer
     * @date 2018-12-03 22:27
     * @param entity 查询条件
     * @return int
     * @version V1.0.0-RELEASE
     */
    int count(T entity);

    /**
     * 根据传入的查询条件, 查询出符合条件的数据的数量
     * 不会自动过滤掉被标记成已删除的数据
     *
     * @author summer
     * @date 2018-12-03 22:29
     * @param entity 查询条件
     * @return int
     * @version V1.0.0-RELEASE
     */
    int countAll(T entity);

    /**
     * 根据传入的排序对象, 获取排序结构的字符串
     *
     * @author summer
     * @date 2018/8/21 上午2:04
     * @param sort 排序对象
     * @return java.lang.String
     * @version V1.0.0-RELEASE
     */
    String getOrderBy(Sort sort);
}