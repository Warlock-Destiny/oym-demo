package com.oym.base.db;

import com.oym.base.db.dao.SysUserDao;
import com.oym.base.db.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @description:
 * @author: zhangyd
 * @date: 2020/10/21 10:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DbApplication.class)
public class TestDemoMethod {
    @Autowired
    private SysUserDao sysUserDao;

    @Test
    public void testDemoMethod() {
        List<SysUser> sysUserList = sysUserDao.demo();
        System.out.println(sysUserList.size());
    }
}
