package com.cn.component.web.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zyd
 * @date 2019/12/11 19:40
 * @desc
 */
@Slf4j
public class DefaultControllerLog implements BaseControllerLog {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void insert(LogInfo logDto) {
        if (logDto == null) {
            log.warn("入参日志为空");
            return;
        }
        try {
            String value = objectMapper.writeValueAsString(logDto);
            log.info("入参日志为:{}", value);
        } catch (JsonProcessingException e) {
            log.warn("入参解析为日志异常", e);
        }
    }
}
