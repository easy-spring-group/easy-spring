package io.easyspring.framework.base.service.impl;

import io.easyspring.framework.base.form.BaseSortForm;
import io.easyspring.framework.base.mapper.BaseMapper;
import io.easyspring.framework.base.model.ExtensionBaseModel;
import io.easyspring.framework.base.service.ExtensionBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 扩展的单表查询的 service 实现类
 *
 * @author summer
 * DateTime 2018-12-03 20:22
 * @version V1.0.0-RELEASE
 */
public class ExtensionBaseServiceImpl<M extends BaseMapper<T>, T extends ExtensionBaseModel>
        extends BaseServiceImpl<M, T>
        implements ExtensionBaseService<T> {

    /**
     * 注入自定义的通用 mapper 的接口
     */
    @SuppressWarnings("all")
    @Autowired
    private M mapper;

    /**
     * 通用的排序方法
     *
     * Author summer
     * DateTime 2018-12-09 15:14
     * @param baseSortFrom 需要排序的对象
     * @param clazz 排序时对应的实体的类型
     * @throws IllegalAccessException 反射异常
     * @throws InstantiationException 实例化异常
     * @return int
     * Version V1.0.0-RELEASE
     */
    @Override
    public int sort(BaseSortForm baseSortFrom, Class<T> clazz) throws IllegalAccessException, InstantiationException {

        // 参数合法性校验
        if (baseSortFrom == null || clazz == null
                || baseSortFrom.getId() == null || baseSortFrom.getId() <= 0
                || baseSortFrom.getEasySort() == null || baseSortFrom.getEasySort() < ExtensionBaseModel.MIN_SORT_SIZE) {
            return 0;
        }

        // 创建需要排序的类的实例
        T entity = clazz.newInstance();
        // 忽略伪删除字段(防止错误修改)
        entity.setDeleted(null);
        // 设置 id
        entity.setId(baseSortFrom.getId());
        // 设置排序值
        entity.setEasySort(baseSortFrom.getEasySort());

        // 执行修改, 并返回影响的数据条数
        return mapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 根据传入的排序对象集合, 获取排序结构的字符串
     *
     * Author summer
     * DateTime 2018/8/21 上午2:04
     * @param baseSortFromList 排序时提交的表单集合
     * @param clazz 排序时对应的实体的类型
     * @throws IllegalAccessException 反射异常
     * @throws InstantiationException 实例化异常
     * @return java.lang.String
     * Version V1.0.0-RELEASE
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int sort(List<BaseSortForm> baseSortFromList, Class<T> clazz)
            throws IllegalAccessException, InstantiationException {
        // 参数合法性校验
        if (CollectionUtils.isEmpty(baseSortFromList) || clazz == null) {
            return 0;
        }

        // 创建实例对象
        T entity = clazz.newInstance();
        // 忽略伪删除字段
        entity.setDeleted(null);

        int changeSize = 0;
        // 循环执行修改
        for (BaseSortForm baseSortForm : baseSortFromList) {
            // 如果参数不合法则直接跳过
            if (baseSortForm == null
                    || baseSortForm.getId() == null || baseSortForm.getId() <= 0
                    || baseSortForm.getEasySort() < T.MIN_SORT_SIZE) {
                continue;
            }

            // 设置要修改成的数据
            entity.setId(baseSortForm.getId());
            entity.setEasySort(baseSortForm.getEasySort());

            // 执行更新, 并判断返回值是否正确
            if (mapper.updateByPrimaryKeySelective(entity) > 0){
                changeSize++;
            }
        }
        // 返回影响的数据量
        return changeSize;
    }
}