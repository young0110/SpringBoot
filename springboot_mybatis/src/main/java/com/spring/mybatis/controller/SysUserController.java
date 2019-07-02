package com.spring.mybatis.controller;

import com.spring.mybatis.model.SysUser;
import com.spring.mybatis.model.common.ResultData;
import com.spring.mybatis.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("{id}")
    public ResultData saveSysUser(@PathVariable String id) {
        return new ResultData<>(sysUserService.findSysUserById(id));
    }



}
