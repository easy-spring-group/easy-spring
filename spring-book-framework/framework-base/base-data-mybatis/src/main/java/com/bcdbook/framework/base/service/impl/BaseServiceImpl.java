package com.bcdbook.framework.base.service.impl;

import com.bcdbook.framework.base.mapper.BaseMapper;
import com.bcdbook.framework.base.model.BaseModel;
import com.bcdbook.framework.base.properties.BasePageProperties;
import com.bcdbook.framework.base.service.BaseService;
import com.bcdbook.framework.common.snowflake.SnowflakeHelp;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 基础单表查询的 service 实现类
 *
 * @author summer
 * @date 2018-12-03 20:22
 * @version V1.0.0-RELEASE
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseModel> implements BaseService<T> {

    /**
     * 注入自定义的通用 mapper 的接口
     */
    @SuppressWarnings("all")
    @Autowired
    private M mapper;

    /**
     * 注入全局唯一 id 生成的工具类
     */
    @SuppressWarnings("all")
    @Autowired
    private SnowflakeHelp snowflakeHelp;

    /**
     * 注入分页的配置
     */
    @Autowired
    private BasePageProperties basePageProperties;

    /**
     * 在执行插入之前由我们自己处理 id 的生成
     *
     * @author summer
     * @date 2018-12-02 22:44
     * @param entity 需要保存的实体对象
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Override
    public void perInsert(T entity) {
        // 设置 id
        entity.setId(snowflakeHelp.nextId());
    }

    /**
     * 用于保存数据的方法, 如果 id 为空则执行添加操作, 否则执行修改操作
     *
     * @author summer
     * @date 2018-12-02 23:48
     * @param entity 需要保存的数据对象
     * @return T
     * @version V1.0.0-RELEASE
     */
    @Override
    public T insertOrUpdateSelective(T entity){
        // 参数合法性校验, 如果传入的对象为空, 则直接返回 0, 表示没有执行
        if(entity == null){
            return null;
        }

        // 设置对象的伪删除标识为未删除状态
        entity.setDeleted(T.DELETED_FALSE);

        // 受影响的数据条数
        int changeSize;
        /*
         * 根据传入的对象是否有 id 来选择执行添加操作还是修改操作
         * 如果有 id, 说明这是已有数据, 则执行添加操作, 否则执行修改操作
         */
        if(StringUtils.isEmpty(entity.getId())){
            perInsert(entity);
            changeSize = mapper.insertSelective(entity);
        } else {
            changeSize = mapper.updateByPrimaryKeySelective(entity);
        }

        // 如果添加或者更新的数量大于 0, 说明有被修改的数据, 则返回修改后的对象, 否则返回空
        return changeSize > 0  ? entity : null;
    }


    /**
     * 用于新建(插入)数据的方法
     *
     * @author summer
     * @date 2018-12-02 23:49
     * @param entity 需要保存的数据对象
     * @return T
     * @version V1.0.0-RELEASE
     */
    @Override
    public T insertAll(T entity) {
        if(entity == null){
            return null;
        }

        // 设置对象的伪删除标识为未删除状态
        entity.setDeleted(T.DELETED_FALSE);

        // 插入之前的操作
        perInsert(entity);

        // 执行插入操作
        int changeSize = mapper.insert(entity);

        // 受影响的数据数量大于 0, 说明有被修改的数据, 则返回修改后的对象, 否则返回空
        return changeSize > 0 ? entity : null;
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @author summer
     * @date 2018-12-02 23:50
     * @param entity 需要保存的实体
     * @return T
     * @version V1.0.0-RELEASE
     */
    @Override
    public T insertSelective(T entity){
        // 参数合法性校验
        if(entity == null){
            return null;
        }

        // 设置对象的伪删除标识为未删除状态
        entity.setDeleted(T.DELETED_FALSE);

        // 插入之前的操作
        perInsert(entity);

        // 执行插入操作
        int changeSize = mapper.insertSelective(entity);

        // 受影响的数据数量大于 0, 说明有被修改的数据, 则返回修改后的对象, 否则返回空
        return changeSize > 0 ? entity : null;
    }

    /**
     * 根据数据的 id 删除(伪删除)数据的方法
     *
     * @author summer
     * @date 2018-12-02 23:50
     * @param id 将要删除(伪删除) 的数据的 id
     * @return java.lang.Integer
     * @version V1.0.0-RELEASE
     */
    @Override
    public Integer delete(Long id){
        // 参数合法性校验
        if(id == null){
            return null;
        }
        // 获取要删除的对象
        T entity = mapper.selectByPrimaryKey(id);
        // 如果对象不存在, 则返回 null
        if (entity == null) {
            return null;
        }
        // 如果对象存在, 设置当前对象的状态为删除状态
        entity.setDeleted(T.DELETED_TRUE);

        // 执行修改操作
        return mapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 根据数据的条件对象删除(伪删除)数据的方法
     *
     * @author summer
     * @date 2018-12-02 23:50
     * @param entity 将要删除(伪删除) 的数据的 条件对象
     * @return java.lang.Integer
     * @version V1.0.0-RELEASE
     */
    @Override
    public Integer deleteSelective(T entity){
        // 参数合法性校验
        if(entity == null){
            return null;
        }

        // 查询条件中强制加入删除标识为未删除
        entity.setDeleted(T.DELETED_FALSE);

        // 从数据库中获取相关对象
        T dbEntity = getByParameters(entity);
        // 如果对象不存在, 则返回 null
        if(dbEntity == null){
            return null;
        }

        // 设置对象的删除状态为已删除
        dbEntity.setDeleted(T.DELETED_TRUE);

        // 执行修改操作
        return mapper.updateByPrimaryKeySelective(dbEntity);
    }

    /**
     * 根据对象的主键, 删除对应的数据对象
     *
     * @author summer
     * @date 2018-12-02 23:51
     * @param id 将要删除的数据的 id
     * @return java.lang.Integer
     * @version V1.0.0-RELEASE
     */
    @Override
    public Integer realDelete(Long id){
        // 参数合法性校验
        if(id == null){
            return null;
        }

        // 执行真实删除
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据实体条件删除对应的数据
     *
     * @author summer
     * @date 2018-12-02 23:51
     * @param entity 筛选条件(使用等号过滤)
     * @return java.lang.Integer
     * @version V1.0.0-RELEASE
     */
    @Override
    public Integer realDeleteSelective(T entity){
        // 参数合法性校验
        if(entity == null){
            return null;
        }

        // 执行真实删除
        return mapper.delete(entity);
    }

    /**
     * 用于更新数据的方法
     *
     * @author summer
     * @date 2018-12-02 23:52
     * @param entity 需要保存的数据对象
     * @return T
     * @version V1.0.0-RELEASE
     */
    @Override
    public T updateAll(T entity) {
        // 参数合法性校验
        if(entity == null || entity.getId() == null){
            return null;
        }

        // 防止误删除
        entity.setDeleted(null);

        // 执行修改操作
        int changeSize = mapper.updateByPrimaryKey(entity);

        // 受影响的数据量大于 0, 则返回修改后的对象, 否则返回空
        return changeSize > 0 ? entity : null;
    }

    /**
     * 根据传入的数据对象, 进行动态的拼接修改语句,如果有值则进行修改,否则直接忽略此字段
     *
     * @author summer
     * @date 2018-12-02 23:52
     * @param entity 需要修改的数据对象
     * @return T
     * @version V1.0.0-RELEASE
     */
    @Override
    public T updateSelective(T entity){
        // 参数合法性校验, 如果传入的对象为空, 或者传入值的id为空, 则直接返回 null, 表示没有执行
        if(entity == null || entity.getId() == null){
            return null;
        }

        // 防止误删除
        entity.setDeleted(null);

        int changeSize = mapper.updateByPrimaryKeySelective(entity);

        // 如果影响的数据量大于 0, 则返回修改后的对象, 否则返回空
        return changeSize > 0 ? entity : null;
    }

    /**
     * 根据 id 查询对应的对象
     *
     * @author summer
     * @date 2018-12-02 23:54
     * @param id 数据的 id
     * @return T
     * @version V1.0.0-RELEASE
     */
    @Override
    public T get(Long id){
        // 参数合法性校验
        if(id == null){
            return null;
        }

        // 执行查询操作
        T entity = mapper.selectByPrimaryKey(id);

        // 伪删除查询
        if(entity != null && entity.getDeleted().equals(T.DELETED_FALSE)){
            return entity;
        }

        return null;
    }

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
    @Override
    public T getByParameters(T entity){
        // 参数合法性校验
        if(entity == null){
            return null;
        }

        // 设置删除状态为未删除
        entity.setDeleted(T.DELETED_FALSE);

        // 执行查询并返回结果
        return mapper.selectOne(entity);
    }

    /**
     * 完全根据查询条件进行查询, 不会自动排查被标记成已删除的记录
     *
     * @author summer
     * @date 2018-12-02 23:54
     * @param entity 查询条件
     * @return T
     * @version V1.0.0-RELEASE
     */
    @Override
    public T getAllByParameters(T entity){
        // 参数合法性校验
        if(entity == null){
            return null;
        }

        // 执行查询并返回结果
        return mapper.selectOne(entity);
    }

    /**
     * 根据传入的实体对象条件, 查询出符合条件的数据的集合
     *
     * @author summer
     * @date 2018-12-02 23:55
     * @param entity 用于封装查询条件的实体
     * @return java.util.List<T>
     * @version V1.0.0-RELEASE
     */
    @Override
    public List<T> list(T entity){
        // 参数合法性校验
        if(entity == null){
            return null;
        }

        // 设置删除状态为未删除
        entity.setDeleted(T.DELETED_FALSE);

        // 执行查询并返回结果
        return mapper.select(entity);
    }

    /**
     * 完全根据查询条件进行查询, 不会自动排查被标记成已删除的记录
     *
     * @author summer
     * @date 2018-12-02 23:55
     * @param entity 查询条件
     * @return java.util.List<T>
     * @version V1.0.0-RELEASE
     */
    @Override
    public List<T> listAll(T entity){
        // 参数合法性校验
        if(entity == null){
            return null;
        }

        // 执行查询并返回结果
        return mapper.select(entity);
    }

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
    @Override
    public Page<T> findPage(T entity){
        // 参数合法性校验
        if(entity == null){
            return null;
        }

        // 设置删除状态为未删除
        entity.setDeleted(T.DELETED_FALSE);

        // 封装自动分页信息
        Page<T> page = PageHelper.startPage(basePageProperties.getDefaultPageNumber(),
                basePageProperties.getDefaultPageSize());
        // 执行查询
        mapper.select(entity);

        // 返回查询结果
        return page;
    }

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
    @Override
    public Page<T> findPageAll(T entity){
        // 参数合法性校验
        if(entity == null){
            return null;
        }

        // 封装自动分页信息
        Page<T> page = PageHelper.startPage(basePageProperties.getDefaultPageNumber(),
                basePageProperties.getDefaultPageSize());
        // 执行查询
        mapper.select(entity);

        // 返回查询结果
        return page;
    }

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
    @Override
    public Page<T> findPage(T entity, String orderBy){
        // 参数校验
        if(entity == null){
            return null;
        }

        // 设置删除状态为未删除
        entity.setDeleted(T.DELETED_FALSE);

        // 封装分页信息
        Page<T> page = PageHelper.startPage(basePageProperties.getDefaultPageNumber(),
                basePageProperties.getDefaultPageSize(),
                orderBy);
        // 执行查询
        mapper.select(entity);

        // 返回查询结果
        return page;
    }

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
    @Override
    public Page<T> findPageAll(T entity, String orderBy){
        // 参数合法性校验
        if(entity == null){
            return null;
        }

        // 封装分页信息
        Page<T> page = PageHelper.startPage(basePageProperties.getDefaultPageNumber(),
                basePageProperties.getDefaultPageSize(),
                orderBy);
        // 执行查询
        mapper.select(entity);

        // 返回查询结果
        return page;
    }

    /**
     * 根据传入的实体条件 / 页码 / 每页显示的数据量
     * 查询出符合条件的分页对象
     *
     * @author summer
     * @date 2018-12-03 21:07
     * @param entity 用于封装条件的实体类
     * @param pageable 分页条件  例如: "update_time desc" 这里的 orderBy 并不会自动转换大小写
     * @return com.github.pagehelper.Page<T>
     * @version V1.0.0-RELEASE
     */
    @Override
    public Page<T> findPage(T entity, Pageable pageable) {
        return findPage(entity, pageable, true);
    }

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
    @Override
    public Page<T> findPageAll(T entity, Pageable pageable) {
        return findPage(entity, pageable, false);
    }

    /**
     * 分页查询的封装
     *
     * @author summer
     * @date 2018-12-03 22:09
     * @param entity 查询条件实体
     * @param pageable 分页信息
     * @param excludeDeleted 是否排序被标记成删除的字段
     * @return com.github.pagehelper.Page<T>
     * @version V1.0.0-RELEASE
     */
    private Page<T> findPage(T entity, Pageable pageable, boolean excludeDeleted) {
        // 校验实体对象
        if(entity == null){
            return null;
        }

        // 如果排除伪删除字段
        if (excludeDeleted) {
            // 设置删除状态为 false, 只查询未被标记删除的
            entity.setDeleted(T.DELETED_FALSE);
        }

        // 校验分页对象, 如果分页对象为空, 则执行普通查询
        if(pageable == null){
            return findPage(entity);
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
        String orderBy = getOrderBy(pageable.getSort());

        // 封装分页信息
        Page<T> page = PageHelper.startPage(pageSize, pageNumber, orderBy);
        // 执行查询
        mapper.select(entity);

        // 返回查询结果
        return page;
    }

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
    @Override
    public PageInfo<T> findPageInfo(T entity) {
        // 参数合法性校验
        if(entity == null){
            return null;
        }

        // 执行分页查询
        Page<T> page = findPage(entity);

        // 封装并返回分页详情信息
        return buildPageInfo(page);
    }


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
    @Override
    public PageInfo<T> findPageInfo(T entity, String orderBy) {
        // 参数校验
        if(entity == null){
            return null;
        }

        // 查询分页信息
        Page<T> page = findPage(entity, orderBy);

        // 封装并返回分页详情信息
        return buildPageInfo(page);
    }

    /**
     * 根据传入的实体条件 / 页码 / 每页显示的数据量
     * 查询出符合条件的分页对象 (包括分页信息详情)
     *
     * @author summer
     * @date 2018-12-03 22:26
     * @param entity 用于封装条件的实体类
     * @param pageable 分页条件 例如: "update_time desc" 这里的 orderBy 并不会自动转换大小写
     * @return com.github.pagehelper.PageInfo<T>
     * @version V1.0.0-RELEASE
     */
    @Override
    public PageInfo<T> findPageInfo(T entity, Pageable pageable) {
        // 参数校验
        if(entity == null){
            return null;
        }

        // 根据条件查询分页信息
        Page<T> page = findPage(entity, pageable);

        // 封装并返回分页详情信息
        return buildPageInfo(page);
    }

    /**
     * 封装分页信息为分页详情信息对象
     *
     * @author summer
     * @date 2018-12-03 22:22
     * @param page 分页信息对象
     * @return com.github.pagehelper.PageInfo<T>
     * @version V1.0.0-RELEASE
     */
    private PageInfo<T> buildPageInfo(Page<T> page){
        // 参数校验
        if (page == null) {
            return null;
        }

        // 创建并返回分页详情对象
        return new PageInfo<>(page);
    }

    /**
     * 根据传入的查询条件, 查询出符合条件的数据的数量
     *
     * @author summer
     * @date 2018-12-03 22:27
     * @param entity 查询条件
     * @return java.lang.Integer
     * @version V1.0.0-RELEASE
     */
    @Override
    public Integer count(T entity){
        // 参数校验
        if(entity == null){
            return 0;
        }

        // 忽略已删除的数据
        entity.setDeleted(T.DELETED_FALSE);

        // 执行计数查询
        return mapper.selectCount(entity);
    }

    /**
     * 根据传入的查询条件, 查询出符合条件的数据的数量
     * 不会自动过滤掉被标记成已删除的数据
     *
     * @author summer
     * @date 2018-12-03 22:29
     * @param entity 查询条件
     * @return java.lang.Integer
     * @version V1.0.0-RELEASE
     */
    @Override
    public Integer countAll(T entity){
        // 参数校验
        if(entity == null){
            return 0;
        }

        // 执行计数查询并返回结果
        return mapper.selectCount(entity);
    }

    /**
     * 根据传入的排序对象, 获取排序结构的字符串
     *
     * @author summer
     * @date 2018-12-03 22:32
     * @param sort 排序对象
     * @return java.lang.String
     * @version V1.0.0-RELEASE
     */
    @Override
    public String getOrderBy(Sort sort){
        if(sort == null || sort.isUnsorted()){
            return null;
        }

        StringBuilder orderBy = new StringBuilder(" ");
        int count = 0;
        // 拼接排序条件
        for(Sort.Order order : sort){
            String property = order.getProperty();
            String direction = order.getDirection().name();
            if(count > 0){
                orderBy.append(", ");
            }
            orderBy.append(property);
            orderBy.append(" ");
            orderBy.append(direction);

            count++;
        }

        return orderBy.toString();
    }
}