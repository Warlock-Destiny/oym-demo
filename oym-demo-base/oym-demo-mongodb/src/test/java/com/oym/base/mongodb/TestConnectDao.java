package com.oym.base.mongodb;

import com.oym.base.mongodb.dao.ConnectDao;
import com.oym.base.mongodb.entity.ConnectInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: zhangyd
 * @date: 2020/10/21 11:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestMongoApplication.class)
public class TestConnectDao {
    @Autowired
    private ConnectDao connectDao;

    @Test
    public void test() {
        ConnectInfo connectInfo = new ConnectInfo();
        connectInfo.setIp("1.1.1.1");
        connectInfo.setPassword("123456");
        connectInfo.setPath("/aa/bb");
        connectInfo.setConnectName("测试连接");
        connectInfo.setUsername("test");
        connectInfo.setPort(8080);
        connectDao.insert(connectInfo);
    }
}
