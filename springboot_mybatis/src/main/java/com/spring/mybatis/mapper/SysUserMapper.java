package com.spring.mybatis.mapper;

import com.spring.mybatis.model.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {

    SysUser findUserById(@Param("id") String id);
}
