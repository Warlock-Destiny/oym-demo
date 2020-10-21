package com.oym.activiti.feign;

import com.oym.activiti.dto.CommentDto;
import com.oym.activiti.dto.HistoricProcessInstanceDto;
import com.oym.activiti.dto.ProcessStartDto;
import com.oym.activiti.query.ProcessQuery;
import com.oym.base.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author zhangyd
 * @date 2019/10/9 17:12
 * @desc
 */
@FeignClient(name = "oym-demo-activiti", path = "/process")
public interface ProcessFeign {

    @PostMapping("start")
    Result<String> startProcess(
            @RequestBody ProcessStartDto processStartDto
    );

    @PostMapping("close")
    Result closeProcess(
            @RequestParam("processInstanceId") String processInstanceId,
            @RequestParam(value = "reason", required = false) String reason
    );

    @GetMapping("hisProcessInstant")
    Result<List<HistoricProcessInstanceDto>> getHisProcessInstantByUserId(
            @RequestBody ProcessQuery processQuery
    );

    @GetMapping("detail")
    Result<List<CommentDto>> detail(
            @RequestBody ProcessQuery processQuery
    );

    @GetMapping("isComplete")
    Result<Boolean> isComplete(
            @RequestParam("processInstantId") String processInstantId
    );

}
