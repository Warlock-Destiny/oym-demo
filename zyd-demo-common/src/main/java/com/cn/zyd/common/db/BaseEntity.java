package com.cn.zyd.common.db;

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
@Accessors(fluent = true)
public abstract class BaseEntity implements Serializable {
    private Long id;
    private Timestamp updateTime;
    private Date createTime;
}
