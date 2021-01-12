package com.oym;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController  {

    @RequestMapping("/test")
    @ResponseBody
    public Result test() {
        String aa="11";
        return new Result().setData(aa);
    }

    @RequestMapping("/test2")
    @ResponseBody
    public Result test2() {
        String aa="22";
        return new Result().setData(aa);
    }
}
