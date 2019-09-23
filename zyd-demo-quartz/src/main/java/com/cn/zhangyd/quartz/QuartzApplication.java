package com.cn.zhangyd.quartz;

import com.cn.zhangyd.quartz.dao.TestDao;
import com.cn.zhangyd.quartz.util.IBatisMapperScan;
import com.cn.zhangyd.quartz.util.SpringHolderUtil;
import com.cn.zhangyd.quartz.util.TestRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@IBatisMapperScan("com.cn.zhangyd.quartz.dao")
@MapperScan("com.cn.zhangyd.quartz.dao")
public class QuartzApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
        TestDao testDao = SpringHolderUtil.getBean(TestDao.class);
        System.out.println(testDao);
    }

}
