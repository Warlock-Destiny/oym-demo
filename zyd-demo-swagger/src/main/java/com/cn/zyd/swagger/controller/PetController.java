package com.cn.zyd.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyd
 * @date 2019/10/12 9:13
 * @desc
 */
@Api("用户控制层")
@RestController
@RequestMapping("/user")
public class PetController {

    @ApiOperation("用户列表")
    @RequestMapping("/list")
    public List<String> findAll(
            @RequestParam(value = "userId") String userId
    ) {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        return list;
    }
}
