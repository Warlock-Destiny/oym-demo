package com.cn.component.mybatisplus.entity;

import java.io.Serializable;

/**
 * @author zyd
 * @date 2019/10/18 11:43
 * @desc 实体抽象类
 */
public interface BaseEntity extends Serializable {
    String ID = "id";
    String CREATE_TIME = "create_time";
    String UPDATE_TIME = "update_time";

    void create();
}
