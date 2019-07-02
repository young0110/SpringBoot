package com.spring.mybatis.service.impl;

import com.spring.mybatis.mapper.SysUserMapper;
import com.spring.mybatis.model.SysUser;
import com.spring.mybatis.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public SysUser findSysUserById(String id) {
        return sysUserMapper.findUserById(id);
    }
}
