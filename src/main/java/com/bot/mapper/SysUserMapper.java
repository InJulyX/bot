package com.bot.mapper;

import com.bot.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SysUserMapper {
    SysUser getSysUser();
}
