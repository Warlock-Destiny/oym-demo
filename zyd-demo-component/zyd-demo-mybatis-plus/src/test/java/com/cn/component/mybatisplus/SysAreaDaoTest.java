package com.cn.component.mybatisplus;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.component.mybatisplus.dao.SysAreaDao;
import com.cn.component.mybatisplus.entity.SysArea;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zyd
 * @date 2020/5/15 18:09
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainTest.class)
public class SysAreaDaoTest {
    @Autowired
    private SysAreaDao sysAreaDao;

    @Test
    public void test() {
        System.out.println(sysAreaDao.selectById(3509L));
    }

    @Test
    public void testUpdateByIdAll() {
        SysArea sysArea = new SysArea();
        sysArea.create();
        sysArea.setId(3509L);
        sysArea.setAreaCode("2");
        sysArea.setParentCode("5");
        sysArea.setName("测试名称");
        sysArea.setLayer(1);
        IPage<SysArea> page = new Page<>();
        sysAreaDao.selectPage(page, null);
    }
}
