package com.oym.base.mongodb.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author zyd
 * @date 2019/10/18 11:43
 * @desc 实体抽象类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseCommonEntity extends BaseEntity {

    @Field(value = "_id")
    private String id;
    // 创建时间
    @Field(value = "create_time")
    protected Date createTime;

}
