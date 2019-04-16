package com.spring.demo.service.impl;

import com.spring.demo.dao.SysUserDAO;
import com.spring.demo.model.SysUser;
import com.spring.demo.model.form.SysUserForm;
import com.spring.demo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

    private final SysUserDAO sysUserDAO;

    @Autowired
    public SysUserServiceImpl(SysUserDAO sysUserDAO) {
        this.sysUserDAO = sysUserDAO;
    }

    @Override
    public String newSysUser(SysUserForm form) {
        SysUser sysUser = form.toInfo(false);
        sysUserDAO.save(sysUser);
        return sysUser.getId();
    }

    @Override
    public int checkSysUser(String userName, String idCardNo, String mobile) {
        int step = 0;
        SysUser sysUser;
        while(step < 3) {
            step++;
            sysUser = new SysUser();
            if (1 == step) sysUser.setUserName(userName);
            if (2 == step) sysUser.setIdCardNo(idCardNo);
            if (3 == step) sysUser.setMobile(mobile);
            sysUser = sysUserDAO.findOne(Example.of(sysUser)).orElse(null);
            if (null != sysUser) return 1;
        }
        return 0;
    }
}
