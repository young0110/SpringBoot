package com.spring.demo.service;

import com.spring.demo.model.form.SysUserForm;

public interface SysUserService {

    int checkSysUser(String userName, String idCardNo, String mobile);

    /**
     * new sysUser info
     * @param form
     * @return id
     */
    String newSysUser(SysUserForm form);
}
