package com.cn.zyd.web.zhangyd.quartz.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestController {

    @Autowired
    private DataSource dataSource;

    public String aa() throws SQLException {
        Connection connection=dataSource.getConnection();
        ResultSet rs=connection.prepareStatement("select count(*) from sakila.actor").executeQuery();
        rs.next();
        System.out.println(rs.getInt(0));;
        return "";
    }
}
