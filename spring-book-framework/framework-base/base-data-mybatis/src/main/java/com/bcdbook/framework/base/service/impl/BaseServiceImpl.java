package com.bcdbook.framework.base.service.impl;

import com.bcdbook.framework.base.mapper.BaseMapper;
import com.bcdbook.framework.base.model.BaseModel;
import com.bcdbook.framework.base.service.BaseService;
import com.bcdbook.framework.common.snowflake.SnowflakeHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseModel> implements BaseService<T> {

    @Autowired
    private M mapper;

    @Autowired
    private SnowflakeHelp snowflakeHelp;

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

        /*
         * 根据传入的对象是否有 id 来选择执行添加操作还是修改操作
         * 如果有 id, 说明这是已有数据, 则执行添加操作, 否则执行修改操作
         */
        if(StringUtils.isEmpty(entity.getId())){
            perInsert(entity);
            mapper.insertSelective(entity);
        } else {
            mapper.updateByPrimaryKeySelective(entity);
        }

        return entity;
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

        perInsert(entity);
        mapper.insert(entity);

        return entity;
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
        if(entity == null){
            return null;
        }

        // 设置对象的伪删除标识为未删除状态
        entity.setDeleted(T.DELETED_FALSE);

        perInsert(entity);
        mapper.insertSelective(entity);

        return entity;
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
        T entity = mapper.selectByPrimaryKey(id);
        if (entity == null) {
            return null;
        }
        entity.setDeleted(T.DELETED_TRUE);

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
        if(dbEntity == null){
            return null;
        }

        dbEntity.setDeleted(T.DELETED_TRUE);

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
        if(entity == null || entity.getId() == null){
            return null;
        }

        // 防止误标示删除
        entity.setDeleted(null);

        mapper.updateByPrimaryKey(entity);

        return entity;
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

        // 防止误标示删除
        entity.setDeleted(null);

        int result = mapper.updateByPrimaryKeySelective(entity);
        // 当无法正常更新时返回 null
        if(result < 1){
            return null;
        }

        return entity;
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

        entity.setDeleted(T.DELETED_FALSE);

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
        if(entity == null){
            return null;
        }

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

        entity.setDeleted(T.DELETED_FALSE);

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

        return mapper.select(entity);
    }

//    /**
//     * @author summer
//     * @date 2017/12/29 下午3:43
//     * @param entity 用于封装条件的实体类
//     * @return com.github.pagehelper.Page<T>
//     * @description 根据传入的实体条件 / 页码 / 每页显示的数据量
//     * 查询出符合条件的分页对象
//     */
//    @Override
//    public Page<T> findPage(T entity){
//        if(entity == null){
//            return null;
//        }
//
//        entity.setDeleted(T.DELETED_FALSE);
//
//        Page<T> page = PageHelper.startPage(entity.getPageNum(), entity.getPageSize());
//        mapper.select(entity);
//
//        return page;
//    }
//
//    /**
//     * @author summer
//     * @date 2018/8/21 下午2:00
//     * @description 根据传入的查询条件查询出所有满足条件的信息, 并进行分页, 不会自动过滤被标记成已删除的数据
//     * @param entity
//     * @return com.github.pagehelper.Page<T>
//     * @version V1.0.0-RELEASE
//     */
//    @Override
//    public Page<T> findPageAll(T entity){
//        if(entity == null){
//            return null;
//        }
//
//        Page<T> page = PageHelper.startPage(entity.getPageNum(), entity.getPageSize());
//        mapper.select(entity);
//
//        return page;
//    }
//
//    /**
//     * @author summer
//     * @date 2017/12/29 下午3:43
//     * @param entity 用于封装条件的实体类
//     * @param orderBy 排序方式 例如: "update_time desc"
//     * @return com.github.pagehelper.Page<T>
//     * @description 根据传入的实体条件 / 页码 / 每页显示的数据量
//     * 查询出符合条件的分页对象
//     */
//    @Override
//    public Page<T> findPage(T entity, String orderBy){
//        if(entity == null){
//            return null;
//        }
//
//        entity.setDeleted(T.DELETED_FALSE);
//
//        Page<T> page = PageHelper.startPage(entity.getPageNum(), entity.getPageSize(), orderBy);
//        mapper.select(entity);
//
//        return page;
//    }
//
//    /**
//     * @author summer
//     * @date 2018/8/21 下午2:03
//     * @description 完全根据查询条件查询符合条件的分页信息, 不会自动过滤被标记成删除的字段
//     * @param entity 条件实体
//     * @param orderBy 排序方式
//     * @return com.github.pagehelper.Page<T>
//     * @version V1.0.0-RELEASE
//     */
//    public Page<T> findPageAll(T entity, String orderBy){
//        if(entity == null){
//            return null;
//        }
//
//        Page<T> page = PageHelper.startPage(entity.getPageNum(), entity.getPageSize(), orderBy);
//        mapper.select(entity);
//
//        return page;
//    }
//
//    /**
//     * @author summer
//     * @date 2017/12/29 下午3:43
//     * @param entity 用于封装条件的实体类
//     * @param pageable 排序方式
//     * @return com.github.pagehelper.Page<T>
//     * @description 根据传入的实体条件 / 页码 / 每页显示的数据量
//     * 查询出符合条件的分页对象
//     */
//    @Override
//    public Page<T> findPage(T entity, Pageable pageable) {
//        // 校验实体对象
//        if(entity == null){
//            return null;
//        }
//
//        entity.setDeleted(T.DELETED_FALSE);
//
//        // 校验排序对象
//        if(pageable == null){
//            return findPage(entity);
//        }
//
//        Integer pageSize = pageable.getPageSize();
//        pageSize = (pageSize == null || pageSize <= 0) ? T.PAGE_SIZE_DEFAULT : pageSize;
//        Integer pageNumber = pageable.getPageNumber();
//        pageNumber = (pageNumber == null || pageNumber <= 0) ? T.PAGE_NUM_DEFAULT : pageNumber;
//
//        // 设置分页信息
//        entity.setPageSize(pageSize);
//        entity.setPageNum(pageNumber);
//
//        // 如果没有排序值
//        if(pageable.getSort().isUnsorted()){
//            return findPage(entity);
//        }
//
//        // 返回有排序值的结果
//        return findPage(entity, getOrderBy(pageable.getSort()));
//    }
//
//    /**
//     * @author summer
//     * @date 2018/8/21 下午2:06
//     * @description 完全根据查询条件查询出符合条件的分页信息, 不会过滤掉被标记成已删除的数据
//     * @param entity 实体条件
//     * @param pageable 分页信息
//     * @return com.github.pagehelper.Page<T>
//     * @version V1.0.0-RELEASE
//     */
//    @Override
//    public Page<T> findPageAll(T entity, Pageable pageable) {
//        // 校验实体对象
//        if(entity == null){
//            return null;
//        }
//
//        // 校验排序对象
//        if(pageable == null){
//            return findPageAll(entity);
//        }
//
//        Integer pageSize = pageable.getPageSize();
//        pageSize = (pageSize <= 0) ? T.PAGE_SIZE_DEFAULT : pageSize;
//        Integer pageNumber = pageable.getPageNumber();
//        pageNumber = (pageNumber <= 0) ? T.PAGE_NUM_DEFAULT : pageNumber;
//
//        // 设置分页信息
//        entity.setPageSize(pageSize);
//        entity.setPageNum(pageNumber);
//
//        // 如果没有排序值
//        if(pageable.getSort().isUnsorted()){
//            return findPageAll(entity);
//        }
//
//        // 返回有排序值的结果
//        return findPageAll(entity, getOrderBy(pageable.getSort()));
//    }
//
//    /**
//     * 根据传入的实体条件 / 页码 / 每页显示的数据量
//     * 查询出符合条件的分页对象 (包括分页信息的详情)
//     * @author summer
//     * @date 2017/12/29 下午3:43
//     * @param entity 用于封装条件的实体类
//     * @return com.github.pagehelper.PageInfo<T>
//     */
//    @Override
//    public PageInfo<T> findPageInfo(T entity) {
//        if(entity == null){
//            return null;
//        }
//
//        Page<T> page = findPage(entity);
//        PageInfo<T> pageInfo = null;
//        if(page != null){
//            pageInfo = new PageInfo<>(page);
//        }
//
//        return pageInfo;
//    }
//
//
//    /**
//     * 根据传入的实体条件 / 页码 / 每页显示的数据量
//     * 查询出符合条件的分页对象 (包括分页信息详情)
//     * @author summer
//     * @date 2017/12/29 下午3:43
//     * @param entity 用于封装条件的实体类
//     * @param orderBy 排序方式 例如: "update_time desc"
//     * @return com.github.pagehelper.PageInfo<T>
//     */
//    @Override
//    public PageInfo<T> findPageInfo(T entity, String orderBy) {
//        // 参数校验
//        if(entity == null){
//            return null;
//        }
//
//        // 查询分页信息
//        Page<T> page = findPage(entity, orderBy);
//        PageInfo<T> pageInfo = null;
//        if(page != null){
//            pageInfo = new PageInfo<>(page);
//        }
//
//        return pageInfo;
//    }
//
//    /**
//     * 根据传入的实体条件 / 页码 / 每页显示的数据量
//     * 查询出符合条件的分页对象 (包括分页信息详情)
//     * @author summer
//     * @date 2017/12/29 下午3:43
//     * @param entity 用于封装条件的实体类
//     * @param pageable 排序方式
//     * @return com.github.pagehelper.PageInfo<T>
//     */
//    @Override
//    public PageInfo<T> findPageInfo(T entity, Pageable pageable) {
//        // 参数校验
//        if(entity == null){
//            return null;
//        }
//
//        // 根据条件查询分页信息
//        Page<T> page = findPage(entity, pageable);
//        PageInfo<T> pageInfo = null;
//        if(page != null){
//            pageInfo = new PageInfo<>(page);
//        }
//
//        return pageInfo;
//    }
//
//    /**
//     * @author summer
//     * @date 2018/8/22 上午11:02
//     * @description 根据传入的查询条件, 查询出符合条件的数据的数量
//     * @param entity 查询条件
//     * @return java.lang.Integer
//     * @version V1.0.0-RELEASE
//     */
//    @Override
//    public Integer count(T entity){
//        if(entity == null){
//            return 0;
//        }
//
//        entity.setDeleted(T.DELETED_FALSE);
//
//        return mapper.selectCount(entity);
//    }
//
//    /**
//     * @author summer
//     * @date 2018/8/22 上午11:02
//     * @description 根据传入的查询条件, 查询出符合条件的数据的数量
//     * 不会自动过滤掉被标记成已删除的数据
//     * @param entity 查询条件
//     * @return java.lang.Integer
//     * @version V1.0.0-RELEASE
//     */
//    @Override
//    public Integer countAll(T entity){
//        if(entity == null){
//            return 0;
//        }
//
//        return mapper.selectCount(entity);
//    }
//
//
//
//    /**
//     * @author summer
//     * @date 2018/8/21 上午2:04
//     * @description 根据传入的排序对象, 获取排序结构的字符串
//     * @param sort 排序对象
//     * @return java.lang.String
//     * @version V1.0.0-RELEASE
//     */
//    @Override
//    public String getOrderBy(Sort sort){
//        if(sort == null || sort.isUnsorted()){
//            return "";
//        }
//
//        StringBuilder orderBy = new StringBuilder(" ");
//        Integer count = 0;
//        // 拼接排序条件
//        for(Sort.Order order : sort){
//            String property = order.getProperty();
//            String direction = order.getDirection().name();
//            if(count > 0){
//                orderBy.append(", ");
//            }
//            orderBy.append(property);
//            orderBy.append(" ");
//            orderBy.append(direction);
//
//            count++;
//        }
//
//        return orderBy.toString();
//    }
}