package com.oym.base.db.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @description:
 * @author: zhangyd
 * @date: 2020/10/21 10:23
 */
@TableName("t_sys_user")
public class SysUser extends BaseCommonEntity {
    public static final String NAME = "name";
    @TableField(NAME)
    private String name;
}
