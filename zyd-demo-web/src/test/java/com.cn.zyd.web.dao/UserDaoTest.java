package com.cn.zyd.web.dao;

import com.cn.zyd.web.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author zhangyd
 * @date 2019/10/20 17:02
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private SysUserDao sysUserDao;

    @Test
    public void testUserInsert() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("zyd")
                .setPassword("123")
                .setUserMail("zyd@qq.com");
        sysUser.setCreateTime(new Date());
        sysUserDao.insert(sysUser);
    }

    @Test
    public void testUpdate() {
        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        sysUser.setUsername("zyd22")
                .setPassword("1234")
                .setUserMail("zyd22@qq.com");
        sysUser.setCreateTime(new Date());
        sysUserDao.updateById(sysUser);
    }

    @Test
    public void testGetById() {
        SysUser sysUser=sysUserDao.getById(1L);
        System.out.println(sysUser);
    }

}
