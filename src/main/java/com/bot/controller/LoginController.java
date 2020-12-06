package com.bot.controller;

import com.bot.entity.SysUser;
import com.bot.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    final
    SysUserMapper sysUserMapper;

    public LoginController(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @RequestMapping(value = "/tttt")
    public SysUser login() {
        SysUser user = sysUserMapper.getSysUser();
        return user;
    }
}
