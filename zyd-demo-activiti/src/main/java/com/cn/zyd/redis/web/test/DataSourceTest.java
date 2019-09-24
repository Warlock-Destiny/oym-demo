package com.cn.zyd.redis.web.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    public void getDataSource() {
        System.out.println(dataSource);
    }
}
