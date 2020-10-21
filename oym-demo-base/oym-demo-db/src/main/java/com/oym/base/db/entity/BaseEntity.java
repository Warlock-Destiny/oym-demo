package com.oym.base.db.entity;

import java.io.Serializable;

/**
 * @author zhangyd
 * @date 2019/10/18 11:43
 * @desc 实体抽象类
 */
public abstract class BaseEntity implements Serializable {

    public static final String ID = "id";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";

}
