package com.cn.zyd;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @description: 备份操作接口
 * @author: zhangyd
 * @date: 2020/09/28 17:11
 */
public class TestJavaConfigBean {
    @Value("${timeout:100}")
    private int timeout;
    private int batch;
    @Value("${topics}")
    private List<String> topic;

    @Value("${batch:200}")
    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getBatch() {
        return batch;
    }

    public List<String> getTopic() {
        return topic;
    }

    public void setTopic(List<String> topic) {
        this.topic = topic;
    }
}
