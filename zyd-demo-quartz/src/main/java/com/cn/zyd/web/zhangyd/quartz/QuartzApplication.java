package com.cn.zyd.web.zhangyd.quartz;

import com.cn.zyd.web.zhangyd.quartz.dao.TestDao;
import com.cn.zyd.web.zhangyd.quartz.util.SpringHolderUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
