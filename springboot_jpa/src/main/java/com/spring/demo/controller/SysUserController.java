package com.spring.demo.controller;

import com.spring.demo.model.common.Msg;
import com.spring.demo.model.common.ResultData;
import com.spring.demo.model.form.SysUserForm;
import com.spring.demo.service.SysUserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("save")
    public ResultData saveSysUser(
        @ApiParam(value = "用户名", required = true) @RequestParam String userName,
        @ApiParam(value = "密码", required = true) @RequestParam String password,
        @ApiParam(value = "姓名", required = true) @RequestParam String realName,
        @ApiParam(value = "性别 0:未知 1:男 2:女 9:未说明", example = "9") @RequestParam(required = false) Integer sex,
        @ApiParam(value = "手机号", required = true) @RequestParam String mobile,
        @ApiParam(value = "身份证号码", required = true) @RequestParam String idCardNo,
        @ApiParam(value = "是否返回用户编号") @RequestParam(required = false) boolean flag
    ) {
        if (1 == sysUserService.checkSysUser(userName, idCardNo, mobile))
            return new ResultData(-1,"用户已存在");
        String id = sysUserService.newSysUser(
                SysUserForm.builder()
                           .userName(userName)
                           .realName(realName)
                           .sex(null == sex ? 9 : sex)
                           .mobile(mobile)
                           .password(password)
                           .idCardNo(idCardNo)
                           .build()
        );
        if (null == id) return new ResultData(Msg.FAIL);
        if (flag) return new ResultData<>(id);
        return new ResultData(Msg.SUCCESS);
    }



}
