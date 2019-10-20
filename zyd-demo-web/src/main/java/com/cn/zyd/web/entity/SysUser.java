package com.cn.zyd.web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cn.zyd.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author zhangyd
 * @date 2019/10/20 16:56
 * @desc
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(schema = "test", value = "t_sys_user")
public class SysUser extends BaseEntity {
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("user_mail")
    private String userMail;
}
