package com.spring.demo.model.form;

import com.spring.demo.model.SysUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SysUserForm {

    private String userName;
    private String password;
    private String mobile;
    private Integer sex;
    private Integer role;
    private String realName;
    private String idCardNo;

    public SysUserForm(String userName, String password, String mobile, Integer sex, Integer role, String realName, String idCardNo) {
        this.userName = userName;
        this.password = password;
        this.mobile = mobile;
        this.sex = sex;
        this.role = role;
        this.realName = realName;
        this.idCardNo = idCardNo;
    }

    public SysUser toInfo(boolean flag) {
        SysUser sysUser = new SysUser();
        if (null != userName) sysUser.setUserName(userName);
        if (null != password) sysUser.setPassword(password);
        if (null != mobile) sysUser.setMobile(mobile);
        if (null != sex) sysUser.setSex(sex);
        if (null != role) sysUser.setRole(role);
        if (null != realName) sysUser.setRealName(realName);
        if (null != idCardNo) sysUser.setIdCardNo(idCardNo);
        return sysUser.complementInfo(flag);
    }
}
