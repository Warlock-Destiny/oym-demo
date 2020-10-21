package com.oym.component.web.log;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhangyd
 * @date 2019/12/11 19:46
 * @desc
 */
@Data
@Accessors(chain = true)
public class LogInfo {
    // 用户id
    private Long userId;
    // 用户名称
    private String username;
    // 日志类型
    private String type;
    // 日志行为
    private String action;
    // 执行时间间隔
    private Long startTime;
    // 执行时间
    private Long execTime;
    // 执行方法
    private String method;
    // 执行入参
    private String params;
    // 访问ip
    private String ip;

}
