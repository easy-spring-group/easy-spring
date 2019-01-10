package com.bcdbook.framework.base.service;

import com.bcdbook.framework.base.dto.BasePageable;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import tk.mybatis.mapper.weekend.Fn;
import tk.mybatis.mapper.weekend.Weekend;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 通用的 service 类
 *
 * @author summer
 * @date 2018-12-05 23:15
 * @version V1.0.0-RELEASE
 */
@Validated
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
     * 使用 sql 语句进行插入操作
     *
     * @author summer
     * @date 2018-12-06 16:45
     * @param sql 想要执行的 sql
     * @return int
     * @version V1.0.0-RELEASE
     */
    int insertUseSql(String sql);

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
     * 根据传入的 sql 执行删除操作, 并返回影响的数据条数
     *
     * @author summer
     * @date 2018-12-06 16:50
     * @param sql 将要执行的 sql 语句
     * @return int
     * @version V1.0.0-RELEASE
     */
    int deleteUseSql(String sql);

    /**
     * 根据数据的 id 删除(伪删除)数据的方法
     *
     * @author summer
     * @date 2018-12-02 23:50
     * @param id 将要删除(伪删除) 的数据的 id
     * @return int
     * @version V1.0.0-RELEASE
     */
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    int realDeleteSelective(T entity);

    /**
     * 根据传入的 sql 执行更新操作并返回影响的数据条数
     *
     * @author summer
     * @date 2018-12-06 16:53
     * @param sql 想要执行的 sql
     * @return int
     * @version V1.0.0-RELEASE
     */
    int updateUseSql(String sql);

    /**
     * 用于更新数据的方法
     *
     * @author summer
     * @date 2018-12-02 23:52
     * @param entity 需要保存的数据对象
     * @return T
     * @version V1.0.0-RELEASE
     */
    @Transactional(rollbackFor = Exception.class)
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
     * 恢复删除的数据
     *
     * @author summer
     * @date 2019-01-10 17:06
     * @param id 想要恢复的数据 id
     * @param clazz 想要恢复的数据的类型
     * @return int
     * @version V1.0.0-RELEASE
     */
    int recoverDeleted(Long id, Class<T> clazz) throws IllegalAccessException, InstantiationException;

    /**
     * 根据传入的 sql 查询出 map 对象
     *
     * @author summer
     * @date 2018-12-05 22:28
     * @param sql 想要执行的 sql
     * @throws MyBatisSystemException 期待查询出一个对象, 如果查询出多个对象, 会抛出此异常
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @version V1.0.0-RELEASE
     */
    Map<String, Object> selectMapUseSql(String sql) throws MyBatisSystemException;

    /**
     * 根据传入的 sql 和对象, 查询出对应的数据对象
     *
     * @author summer
     * @date 2018-12-06 16:57
     * @param sql 想要执行的 sql
     * @param beanClass 想要返回的数据对象
     * @throws IllegalAccessException 反射异常
     * @throws InstantiationException 实例化异常
     * @throws InvocationTargetException 转换时的数据类型异常
     * @throws MyBatisSystemException 期待查询出一个对象, 如果查询出多个对象, 会抛出此异常
     * @throws IOException 执行对象转换的时候可能出现的的 io 异常
     * @return B
     * @version V1.0.0-RELEASE
     */
    <B> B selectMapUseSql(String sql, Class<B> beanClass)
            throws IllegalAccessException, InstantiationException,
            InvocationTargetException, MyBatisSystemException, IOException;

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
     * @throws MyBatisSystemException 当查询出多条数据时会抛出此异常
     * @version V1.0.0-RELEASE
     */
    T getByParameters(T entity) throws MyBatisSystemException;

    /**
     * 完全根据查询条件进行查询, 不会自动排查被标记成已删除的记录
     *
     * @author summer
     * @date 2018-12-02 23:54
     * @param entity 查询条件
     * @return T
     * @throws MyBatisSystemException 当查询出多条数据时会抛出此异常
     * @version V1.0.0-RELEASE
     */
    T getAllByParameters(T entity) throws MyBatisSystemException;

    /**
     * 根据传入的 sql 查询出对应的数据集合
     *
     * @author summer
     * @date 2018-12-06 17:05
     * @param sql 将要执行的 sql
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @version V1.0.0-RELEASE
     */
    List<Map<String, Object>> selectListUseSql(String sql);

    /**
     * 根据传入的 sql 查询出符合条件的集合对象
     *
     * @author summer
     * @date 2018-12-06 17:10
     * @param sql 将要执行的 sql
     * @param beanClass 想要返回的对象的 class
     * @throws IllegalAccessException 反射异常
     * @throws InstantiationException 实例化异常
     * @throws InvocationTargetException 转换时的数据类型异常
     * @throws MyBatisSystemException 期待查询出一个对象, 如果查询出多个对象, 会抛出此异常
     * @throws IOException 执行对象转换的时候可能出现的的 io 异常
     * @return java.util.List<B>
     * @version V1.0.0-RELEASE
     */
    <B> List<B> selectListUseSql(String sql, Class<B> beanClass)
            throws IllegalAccessException, InstantiationException, InvocationTargetException,
            MyBatisSystemException, IOException;

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
     * 根据封装的查询条件和分页信息, 查询符合条件的分页信息
     *
     * @author summer
     * @date 2019-01-10 15:28
     * @param weekend 动态查询条件
     * @param pageable 分页信息
     * @return com.github.pagehelper.Page<T>
     * @version V1.0.0-RELEASE
     */
    Page<T> findPage(Weekend<T> weekend, Pageable pageable);

    /**
     * 封装分页信息为分页详情信息对象
     *
     * @author summer
     * @date 2018-12-03 22:22
     * @param page 分页信息对象
     * @return com.github.pagehelper.PageInfo<T>
     * @version V1.0.0-RELEASE
     */
    PageInfo<T> buildPageInfo(Page<T> page);

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
     * 验证对应的数据是否已经存在
     *
     * @author summer
     * @date 2019-01-08 16:20
     * @param function 想要验证的字段
     * @param value 需要验证的值
     * @param clazz 需要验证的类
     * @return boolean
     * @version V1.0.0-RELEASE
     */
    boolean valueExist(@NotNull(message = "需要验证的字段不能为空") Fn<T, Object> function,
                       Object value,
                       @NotNull(message = "需要验证的对象实体不能为空") Class<T> clazz);

    /**
     * 在排除自身的情况下检查对应的值是否存在
     *
     * @author summer
     * @date 2019-01-08 16:27
     * @param function 想要验证的字段
     * @param value 需要验证的值
     * @param id 自身的 id
     * @param clazz 需要验证的类
     * @return boolean
     * @version V1.0.0-RELEASE
     */
    boolean valueExistWithoutSelf(@NotNull(message = "需要验证的字段不能为空") Fn<T, Object> function,
                                  Object value,
                                  Long id,
                                  @NotNull(message = "需要验证的对象实体不能为空") Class<T> clazz);

    /**
     * 封装自定义的分页信息
     *
     * @author summer
     * @date 2019-01-10 15:21
     * @param pageable spring 的 pageable 对象
     * @return com.bcdbook.framework.base.dto.BasePageable
     * @version V1.0.0-RELEASE
     */
    BasePageable getBasePageable(Pageable pageable);

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