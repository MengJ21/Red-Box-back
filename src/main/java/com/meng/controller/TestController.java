package com.meng.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.meng.util.Result;

@RestController
public class TestController {
    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping("/test")
    //@PreAuthorize("hasAnyRole('admin')")
    public Result hello(){
        return Result.success("信息返回成功","你好");
    }
}
