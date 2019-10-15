package com.cn.zyd.activiti.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.activiti.engine.task.Comment;

import java.util.Date;

/**
 * @author zyd
 * @date 2019/10/10 15:51
 * @desc
 */
@Data
@Accessors(chain = true)
public class CommentDto implements Comment {

    private String id;
    private String userId;
    private String taskId;
    private String processInstanceId;
    private Date time;
    private String fullMessage;

    @Override
    public String getType() {
        return null;
    }
}
