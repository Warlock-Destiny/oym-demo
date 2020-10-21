package com.oym.activiti.dto;

import java.util.HashMap;

/**
 * @author zhangyd
 * @date 2019/10/29 16:33
 * @desc 任务必须要有的参数
 */
public class TaskParam extends HashMap<String, Object> {

    public TaskParam() {
    }

    public TaskParam(String key, Object value) {
        putAndReturn(key, value);
    }

    public TaskParam putAndReturn(String key, Object value) {
        put(key, value);
        return this;
    }
}
