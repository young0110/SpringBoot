package com.spring.demo.service;

import com.spring.demo.model.form.SysUserForm;

public interface SysUserService {

    /**
     * new sysUser info
     * @param form
     * @return id
     */
    String newSysUser(SysUserForm form);
}
