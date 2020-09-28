package com.cn.zyd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 备份操作接口
 * @author: zhangyd
 * @date: 2020/09/28 17:11
 */
@RestController
public class TestController {

    @Autowired
    private TestJavaConfigBean testJavaConfigBean;

    @RequestMapping("get")
    public String get(){
        int batch = testJavaConfigBean.getBatch();
        int timeout = testJavaConfigBean.getTimeout();
        return "batch:"+batch+", timeout:"+timeout;
    }

}