package com.cn.zyd.activiti.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.activiti.engine.history.HistoricTaskInstance;

import java.util.Date;

/**
 * @author zyd
 * @date 2019/10/9 14:22
 * @desc
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class HisTaskDto extends TaskDto implements HistoricTaskInstance {
    private Date startTime;
    private Date endTime;
    private Date time;
    private Long durationInMillis;
    private Long workTimeInMillis;
    private String deleteReason;
}
