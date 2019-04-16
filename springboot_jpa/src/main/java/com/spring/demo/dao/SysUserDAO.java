package com.spring.demo.dao;

import com.spring.demo.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserDAO extends JpaRepository<SysUser, String> {}
