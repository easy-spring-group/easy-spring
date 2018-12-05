package com.bcdbook.framework.base.data.mybatis.test.service.impl;

import com.bcdbook.framework.base.data.mybatis.test.model.User;
import com.bcdbook.framework.base.data.mybatis.test.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 用户的 service 测试类
 *
 * @author summer
 * @date 2018-12-04 22:16
 * @version V1.0.0-RELEASE
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceImplTest {

    /**
     * 注入异常断言
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * 注入用户的 service
     */
    @Resource
    private UserService userService;

    /**
     * 测试生成全局唯一 id 的方法
     *
     * @author summer
     * @date 2018-12-04 22:17
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void perInsert() {
        User user = new User();
        userService.perInsert(user);
        assertNotNull(user.getId());
        assertTrue(user.getId() > 0);
    }


    /**
     * 插入或更新数据的测试(测试插入)
     *
     * @author summer
     * @date 2018-12-04 22:30
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void insertOrUpdateSelectiveInsert(){
        String name = "summer-insert-or-update";

        User user = new User();
        user.setName(name);

        User userResult = userService.insertOrUpdateSelective(user);
        assertNotNull(userResult);
        assertNotNull(userResult.getId());
        assertTrue(user.getId() > 0);
        assertEquals(name, userResult.getName());
    }


    /**
     * 插入或更新数据的测试(测试修改)
     *
     * @author summer
     * @date 2018-12-04 22:30
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void insertOrUpdateSelectiveUpdate(){
        Long id = 1428117721710592L;
        String name = "update-name";

        User user = new User();
        user.setId(id);
        user.setName(name);

        User userResult = userService.insertOrUpdateSelective(user);
        assertNotNull(userResult);

        assertNotNull(userResult.getId());
        assertEquals(id, userResult.getId());

        assertEquals(name, userResult.getName());
    }

    /**
     * 插入或更新数据的测试(测试修改)
     *
     * @author summer
     * @date 2018-12-04 22:30
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void insertOrUpdateSelectiveUpdateNull(){
        String name = "name-not-exist";
        Long notExistId = 123L;

        User user = new User();
        user.setId(notExistId);
        user.setName(name);

        User userResult = userService.insertOrUpdateSelective(user);
        assertNull(userResult);
    }

    /**
     * 测试插入数据(全插入)
     *
     * @author summer
     * @date 2018-12-04 22:59
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void insertAll(){
        String nameInsertAll = "string-name-insert-all";

        User user = new User();
        user.setName(nameInsertAll);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        User userResult = userService.insertAll(user);
        assertNotNull(userResult);
        assertNotNull(userResult.getId());
        assertEquals(nameInsertAll, userResult.getName());
    }

    /**
     * 测试插入数据(选择性插入)
     *
     * @author summer
     * @date 2018-12-04 23:05
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void insertSelective(){
        String nameInsertSelective = "name-insert-selective";

        User user = new User();
        user.setName(nameInsertSelective);

        User userResult = userService.insertSelective(user);
        assertNotNull(userResult);
        assertNotNull(userResult.getId());
        assertEquals(nameInsertSelective, userResult.getName());
    }

    /**
     * 删除测试(正常删除)
     *
     * @author summer
     * @date 2018-12-04 23:07
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void delete(){
        Long id = 1428117721710593L;
        int deleted = userService.delete(id);
        assertTrue(deleted > 0);
    }

    /**
     * 删除测试(未找到数据)
     *
     * @author summer
     * @date 2018-12-04 23:07
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void deleteNoData(){
        Long id = 123L;
        int deleted = userService.delete(id);
        assertEquals(0, deleted);
    }

    /**
     * 按照条件删除(有数据)
     *
     * @author summer
     * @date 2018-12-04 23:11
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void deleteSelective(){
        String name = "delete-data";

        User user = new User();
        user.setName(name);
        int deleted = userService.deleteSelective(user);
        assertTrue(deleted > 0);
    }

    /**
     * 按照条件删除(无数据)
     *
     * @author summer
     * @date 2018-12-04 23:11
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void deleteSelectiveNoData(){
        String name = "delete-data-not-exist";
        User user = new User();
        user.setName(name);
        int deleted = userService.deleteSelective(user);
        assertEquals(0, deleted);
    }



    /**
     * 真实删除测试(正常删除)
     *
     * @author summer
     * @date 2018-12-04 23:07
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void realDelete(){
        Long id = 1428117721710596L;
        int deleted = userService.realDelete(id);
        assertTrue(deleted > 0);
    }

    /**
     * 真实删除测试(未找到数据)
     *
     * @author summer
     * @date 2018-12-04 23:07
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void realDeleteNoData(){
        Long id = 123L;
        // 此 id 是不存在的
        int deleted = userService.realDelete(id);
        assertEquals(0, deleted);
    }


    /**
     * 按照条件删除(有数据)
     *
     * @author summer
     * @date 2018-12-04 23:11
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void realDeleteSelective(){
        String name = "real-delete-data";
        User user = new User();
        user.setName(name);
        int deleted = userService.realDeleteSelective(user);
        assertTrue(deleted > 0);
    }

    /**
     * 按照条件删除(无数据)
     *
     * @author summer
     * @date 2018-12-04 23:11
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void realDeleteSelectiveNoData(){
        String name = "real-delete-data-not-exist";
        User user = new User();
        user.setName(name);
        int deleted = userService.realDeleteSelective(user);
        assertEquals(0, deleted);
    }

    /**
     * 全字段更新的测试
     *
     * @author summer
     * @date 2018-12-05 14:33
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void updateAll(){
        Long id = 1428117721710599L;
        String name = "update-all";
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        User userResult = userService.updateAll(user);
        assertNotNull(userResult);

        assertNotNull(userResult.getId());
        assertEquals(id, userResult.getId());

        assertNotNull(userResult.getName());
        assertEquals(name, userResult.getName());
    }

    /**
     * 当对应的 id 不存在时, 执行全局的修改
     *
     * @author summer
     * @date 2018-12-05 14:41
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void updateAllNotExist(){
        Long id = 123L;
        String name = "update-all-not-exist";
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        User userResult = userService.updateAll(user);
        assertNull(userResult);

    }

    /**
     * 全字段更新的测试
     *
     * @author summer
     * @date 2018-12-05 14:33
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void updateSelective(){
        Long id = 1428117721710600L;
        String name = "update-selective";
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        User userResult = userService.updateSelective(user);
        assertNotNull(userResult);

        assertNotNull(userResult.getId());
        assertEquals(id, userResult.getId());

        assertNotNull(userResult.getName());
        assertEquals(name, userResult.getName());
    }


    /**
     * 当对应的 id 不存在时, 执行部分的修改
     *
     * @author summer
     * @date 2018-12-05 14:41
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void updateSelectiveNotExist(){
        Long id = 123L;
        String name = "update-selective-not-exist";
        User user = new User();
        user.setId(id);
        user.setName(name);

        User userResult = userService.updateAll(user);
        assertNull(userResult);
    }

    /**
     * 测试根据 id 查询
     *
     * @author summer
     * @date 2018-12-05 15:00
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void get(){
        Long id = 1428117721710601L;
        String name = "test-get";
        User userResult = userService.get(id);
        assertNotNull(userResult);
        assertNotNull(userResult.getName());
        assertEquals(name, userResult.getName());
    }

    /**
     * 测试根据 id 查询(没有存在的数据)
     *
     * @author summer
     * @date 2018-12-05 15:00
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void getNotExist(){
        Long id = 123L;
        User userResult = userService.get(id);
        assertNull(userResult);
    }

    /**
     * 根据条件查询
     *
     * @author summer
     * @date 2018-12-05 15:07
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void getByParameters(){
        String name = "test-get-one";
        User user = new User();
        user.setName(name);

        User userResult= userService.getByParameters(user);
        assertNotNull(userResult);
    }

    /**
     * 根据条件查询(没有存在的数据)
     *
     * @author summer
     * @date 2018-12-05 15:07
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void getByParametersNotExist(){
        String name = "test-get-one-not-exist";
        User user = new User();
        user.setName(name);

        User userResult= userService.getByParameters(user);
        assertNull(userResult);
    }

    /**
     * 根据条件查询
     *
     * @author summer
     * @date 2018-12-05 15:07
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void getByParametersException(){
        // 因为数据库中有两条符合条件的数据, 此处预测会有异常
        expectedException.expect(MyBatisSystemException.class);
        String name = "test-get";
        User user = new User();
        user.setName(name);

        User userResult= userService.getByParameters(user);
        assertNotNull(userResult);
    }


    /**
     * 根据条件查询
     *
     * @author summer
     * @date 2018-12-05 15:07
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void getAllByParameters(){
        String name = "test-get-one-deleted";
        User user = new User();
        user.setName(name);
        user.setDeleted(null);

        User userResult= userService.getAllByParameters(user);
        assertNotNull(userResult);
    }

    /**
     * 根据条件查询(没有存在的数据)
     *
     * @author summer
     * @date 2018-12-05 15:07
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void getAllByParametersNotExist(){
        String name = "test-get-one-not-exist";
        User user = new User();
        user.setName(name);

        User userResult= userService.getAllByParameters(user);
        assertNull(userResult);
    }

    /**
     * 根据条件查询
     *
     * @author summer
     * @date 2018-12-05 15:07
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void getAllByParametersException(){
        // 因为数据库中有两条符合条件的数据, 此处预测会有异常
        expectedException.expect(MyBatisSystemException.class);
        String name = "test-get-one-deleted-2";
        User user = new User();
        user.setName(name);
        user.setDeleted(null);

        User userResult= userService.getAllByParameters(user);
        assertNotNull(userResult);
    }

    /**
     * 集合查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void list(){
        User user = new User();
        user.setName("test-get");

        List<User> userList = userService.list(user);
        assertNotNull(userList);
        assertEquals(2, userList.size());
    }

    /**
     * 集合查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void listWithOutDeleted(){
        User user = new User();
        user.setName("test-get-one-deleted-2");
        user.setDeleted(null);

        List<User> userList = userService.list(user);
        assertNotNull(userList);
        assertEquals(1, userList.size());
    }

    /**
     * 集合查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void listNoData(){
        User user = new User();
        user.setName("test-get-no-data");

        List<User> userList = userService.list(user);
        assertTrue(CollectionUtils.isEmpty(userList));
    }


    /**
     * 集合查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void listAll(){
        User user = new User();
        user.setName("test-get");

        List<User> userList = userService.listAll(user);
        assertNotNull(userList);
        assertEquals(2, userList.size());
    }

    /**
     * 集合查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void listAllHasDeleted(){
        User user = new User();
        user.setName("test-get-one-deleted-2");
        user.setDeleted(null);

        List<User> userList = userService.listAll(user);
        assertNotNull(userList);
        assertEquals(2, userList.size());
    }

    /**
     * 集合查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void listAllNoData(){
        User user = new User();
        user.setName("test-get-no-data");

        List<User> userList = userService.listAll(user);
        assertTrue(CollectionUtils.isEmpty(userList));
    }

    /**
     * 分页查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPage(){
        User user = new User();
        user.setName("test-get");

        Page<User> userPage = userService.findPage(user);
        assertNotNull(userPage);
        assertEquals(2, userPage.size());
    }

    /**
     * 分页查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageWithOutDeleted(){
        User user = new User();
        user.setName("test-get-one-deleted-2");
        user.setDeleted(null);

        Page<User> userPage = userService.findPage(user);
        assertNotNull(userPage);
        assertEquals(1, userPage.size());
    }

    /**
     * 分页查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageNoData(){
        User user = new User();
        user.setName("test-get-no-data");

        Page<User> userPage = userService.findPage(user);
        assertTrue(CollectionUtils.isEmpty(userPage));
    }


    /**
     * 分页查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageAll(){
        User user = new User();
        user.setName("test-get");

        Page<User> userPage = userService.findPageAll(user);
        assertNotNull(userPage);
        assertEquals(2, userPage.size());
    }

    /**
     * 分页查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageAllHasDeleted(){
        User user = new User();
        user.setName("test-get-one-deleted-2");
        user.setDeleted(null);

        Page<User> userPage = userService.findPageAll(user);
        assertNotNull(userPage);
        assertEquals(2, userPage.size());
    }

    /**
     * 分页查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageAllNoData(){
        User user = new User();
        user.setName("test-get-no-data");

        Page<User> userPage = userService.findPageAll(user);
        assertTrue(CollectionUtils.isEmpty(userPage));
    }

    /**
     * 分页并且排序
     *
     * @author summer
     * @date 2018-12-05 19:06
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageOrder(){
        User user = new User();
        user.setName("test-get");

        Page<User> userPageAsc = userService.findPage(user, "id asc");
        assertNotNull(userPageAsc);
        Long firstId = 1428117721710601L;
        assertEquals(firstId , userPageAsc.get(0).getId());


        Page<User> userPageDesc = userService.findPage(user, "id desc");
        assertNotNull(userPageDesc);
        Long lastId = 1428117721710603L;
        assertEquals(lastId , userPageDesc.get(0).getId());
    }


    /**
     * 分页并且排序
     *
     * @author summer
     * @date 2018-12-05 19:06
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageAllOrder(){
        User user = new User();
        user.setName("test-get-one-deleted-2");
        user.setDeleted(null);

        Page<User> userPageAsc = userService.findPageAll(user, "id asc");
        assertNotNull(userPageAsc);
        Long firstId = 1428117721710605L;
        assertEquals(firstId , userPageAsc.get(0).getId());

        Page<User> userPageDesc = userService.findPageAll(user, "id desc");
        assertNotNull(userPageDesc);
        Long lastId = 1428117721710606L;
        assertEquals(lastId , userPageDesc.get(0).getId());
    }

    /**
     * 根据传入条件和分页条件, 查询分页信息
     *
     * @author summer
     * @date 2018-12-05 19:35
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPagePageable(){
        User user = new User();
        user.setName("test-get");

        // 増序排列校验
        Sort sortAsc = new Sort(Sort.Direction.ASC, "id");
        Pageable pageableAsc = PageRequest.of(1, 10, sortAsc);
        Page<User> userPageAsc = userService.findPage(user, pageableAsc);
        assertNotNull(userPageAsc);
        assertEquals(2, userPageAsc.size());
        Long firstId = 1428117721710601L;
        assertEquals(firstId , userPageAsc.get(0).getId());

        // 倒序排列校验
        Sort sortDesc = new Sort(Sort.Direction.DESC, "id");
        Pageable pageableDesc = PageRequest.of(1, 10, sortDesc);
        Page<User> userPageDesc = userService.findPage(user, pageableDesc);
        assertNotNull(userPageDesc);
        assertEquals(2, userPageAsc.size());
        Long lastId = 1428117721710603L;
        assertEquals(lastId , userPageDesc.get(0).getId());

        // limit 校验
        Pageable pageableLimit = PageRequest.of(1, 1);
        Page<User> userPageLimit = userService.findPage(user, pageableLimit);
        assertNotNull(userPageLimit);
        assertEquals(1, userPageLimit.size());

    }


    /**
     * 根据传入条件和分页条件, 查询分页信息
     *
     * @author summer
     * @date 2018-12-05 19:35
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageAllPageable(){
        User user = new User();
        user.setName("test-get-one-deleted-2");
        user.setDeleted(null);

        // 増序排列校验
        Sort sortAsc = new Sort(Sort.Direction.ASC, "id");
        Pageable pageableAsc = PageRequest.of(1, 10, sortAsc);
        Page<User> userPageAsc = userService.findPageAll(user, pageableAsc);
        assertNotNull(userPageAsc);
        assertEquals(2, userPageAsc.size());
        Long firstId = 1428117721710605L;
        assertEquals(firstId , userPageAsc.get(0).getId());

        // 倒序排列校验
        Sort sortDesc = new Sort(Sort.Direction.DESC, "id");
        Pageable pageableDesc = PageRequest.of(1, 10, sortDesc);
        Page<User> userPageDesc = userService.findPageAll(user, pageableDesc);
        assertNotNull(userPageDesc);
        assertEquals(2, userPageAsc.size());
        Long lastId = 1428117721710606L;
        assertEquals(lastId , userPageDesc.get(0).getId());

        // limit 校验
        Pageable pageableLimit = PageRequest.of(1, 1);
        Page<User> userPageLimit = userService.findPageAll(user, pageableLimit);
        assertNotNull(userPageLimit);
        assertEquals(1, userPageLimit.size());

    }









    /**
     * 分页查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageInfo(){
        User user = new User();
        user.setName("test-get");

        PageInfo<User> userPageInfo = userService.findPageInfo(user);
        assertNotNull(userPageInfo);
        assertEquals(2, userPageInfo.getList().size());
    }

    /**
     * 分页查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageInfoWithOutDeleted(){
        User user = new User();
        user.setName("test-get-one-deleted-2");
        user.setDeleted(null);

        PageInfo<User> userPageInfo = userService.findPageInfo(user);
        assertNotNull(userPageInfo);
        assertEquals(1, userPageInfo.getList().size());
    }

    /**
     * 分页查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageInfoNoData(){
        User user = new User();
        user.setName("test-get-no-data");

        PageInfo<User> userPageInfo = userService.findPageInfo(user);
        assertTrue(CollectionUtils.isEmpty(userPageInfo.getList()));
    }


    /**
     * 分页查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageInfoAll(){
        User user = new User();
        user.setName("test-get");

        PageInfo<User> userPageInfo = userService.findPageInfoAll(user);
        assertNotNull(userPageInfo);
        assertEquals(2, userPageInfo.getList().size());
    }

    /**
     * 分页查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageInfoAllHasDeleted(){
        User user = new User();
        user.setName("test-get-one-deleted-2");
        user.setDeleted(null);

        PageInfo<User> userPageInfo = userService.findPageInfoAll(user);
        assertNotNull(userPageInfo);
        assertEquals(2, userPageInfo.getList().size());
    }

    /**
     * 分页查询的测试
     *
     * @author summer
     * @date 2018-12-05 18:52
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageInfoAllNoData(){
        User user = new User();
        user.setName("test-get-no-data");

        PageInfo<User> userPageInfo = userService.findPageInfoAll(user);
        assertTrue(CollectionUtils.isEmpty(userPageInfo.getList()));
    }

    /**
     * 分页并且排序
     *
     * @author summer
     * @date 2018-12-05 19:06
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageInfoOrder(){
        User user = new User();
        user.setName("test-get");

        PageInfo<User> userPageInfoAsc = userService.findPageInfo(user, "id asc");
        assertNotNull(userPageInfoAsc);
        Long firstId = 1428117721710601L;
        assertEquals(firstId , userPageInfoAsc.getList().get(0).getId());


        PageInfo<User> userPageInfoDesc = userService.findPageInfo(user, "id desc");
        assertNotNull(userPageInfoDesc);
        Long lastId = 1428117721710603L;
        assertEquals(lastId , userPageInfoDesc.getList().get(0).getId());
    }


    /**
     * 分页并且排序
     *
     * @author summer
     * @date 2018-12-05 19:06
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageInfoAllOrder(){
        User user = new User();
        user.setName("test-get-one-deleted-2");
        user.setDeleted(null);

        PageInfo<User> userPageInfoAsc = userService.findPageInfoAll(user, "id asc");
        assertNotNull(userPageInfoAsc);
        Long firstId = 1428117721710605L;
        assertEquals(firstId , userPageInfoAsc.getList().get(0).getId());

        PageInfo<User> userPageInfoDesc = userService.findPageInfoAll(user, "id desc");
        assertNotNull(userPageInfoDesc);
        Long lastId = 1428117721710606L;
        assertEquals(lastId , userPageInfoDesc.getList().get(0).getId());
    }

    /**
     * 根据传入条件和分页条件, 查询分页信息
     *
     * @author summer
     * @date 2018-12-05 19:35
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageInfoPageable(){
        User user = new User();
        user.setName("test-get");

        // 増序排列校验
        Sort sortAsc = new Sort(Sort.Direction.ASC, "id");
        Pageable pageableAsc = PageRequest.of(1, 10, sortAsc);
        PageInfo<User> userPageInfoAsc = userService.findPageInfo(user, pageableAsc);
        assertNotNull(userPageInfoAsc);
        assertEquals(2, userPageInfoAsc.getList().size());
        Long firstId = 1428117721710601L;
        assertEquals(firstId , userPageInfoAsc.getList().get(0).getId());

        // 倒序排列校验
        Sort sortDesc = new Sort(Sort.Direction.DESC, "id");
        Pageable pageableDesc = PageRequest.of(1, 10, sortDesc);
        PageInfo<User> userPageInfoDesc = userService.findPageInfo(user, pageableDesc);
        assertNotNull(userPageInfoDesc);
        assertEquals(2, userPageInfoAsc.getList().size());
        Long lastId = 1428117721710603L;
        assertEquals(lastId , userPageInfoDesc.getList().get(0).getId());

        // limit 校验
        Pageable pageableLimit = PageRequest.of(1, 1);
        PageInfo<User> userPageInfoLimit = userService.findPageInfo(user, pageableLimit);
        assertNotNull(userPageInfoLimit);
        assertEquals(1, userPageInfoLimit.getList().size());

    }


    /**
     * 根据传入条件和分页条件, 查询分页信息
     *
     * @author summer
     * @date 2018-12-05 19:35
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void findPageInfoAllPageable(){
        User user = new User();
        user.setName("test-get-one-deleted-2");
        user.setDeleted(null);

        // 増序排列校验
        Sort sortAsc = new Sort(Sort.Direction.ASC, "id");
        Pageable pageableAsc = PageRequest.of(1, 10, sortAsc);
        PageInfo<User> userPageInfoAsc = userService.findPageInfoAll(user, pageableAsc);
        assertNotNull(userPageInfoAsc);
        assertEquals(2, userPageInfoAsc.getList().size());
        Long firstId = 1428117721710605L;
        assertEquals(firstId , userPageInfoAsc.getList().get(0).getId());

        // 倒序排列校验
        Sort sortDesc = new Sort(Sort.Direction.DESC, "id");
        Pageable pageableDesc = PageRequest.of(1, 10, sortDesc);
        PageInfo<User> userPageInfoDesc = userService.findPageInfoAll(user, pageableDesc);
        assertNotNull(userPageInfoDesc);
        assertEquals(2, userPageInfoAsc.getList().size());
        Long lastId = 1428117721710606L;
        assertEquals(lastId , userPageInfoDesc.getList().get(0).getId());

        // limit 校验
        Pageable pageableLimit = PageRequest.of(1, 1);
        PageInfo<User> userPageInfoLimit = userService.findPageInfoAll(user, pageableLimit);
        assertNotNull(userPageInfoLimit);
        assertEquals(1, userPageInfoLimit.getList().size());

    }

    /**
     * 对数据进行计数
     *
     * @author summer
     * @date 2018-12-05 21:48
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void count(){
        User user = new User();
        user.setName("test-get");

        int count = userService.count(user);
        assertEquals(2, count);
    }

    /**
     * 对数据进行计数
     *
     * @author summer
     * @date 2018-12-05 21:48
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void countNoData(){
        User user = new User();
        user.setName("test-get-no-data");

        int count = userService.count(user);
        assertEquals(0, count);
    }

    /**
     * 对数据进行计数
     *
     * @author summer
     * @date 2018-12-05 21:48
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void countAll(){
        User user = new User();
        user.setName("test-get-one-deleted-2");
        user.setDeleted(null);

        int count = userService.countAll(user);
        assertEquals(2, count);
    }

    /**
     * 对数据进行计数
     *
     * @author summer
     * @date 2018-12-05 21:48
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void countAllNoData(){
        User user = new User();
        user.setName("test-get-one-deleted-2-no-data");
        user.setDeleted(null);

        int count = userService.countAll(user);
        assertEquals(0, count);
    }


}