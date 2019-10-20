package com.cn.zyd.base.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author zyd
 * @date 2019/9/26 14:20
 * @desc 基础实体类
 */
@Data
@Accessors(chain = true)
public abstract class BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField("create_time")
    private Date createTime;
    @TableField(value = "update_time", insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL)
    private Timestamp updateTime;
}
