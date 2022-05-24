package com.example.mapper;

import com.example.entity.SysPermission;
import com.example.entity.SysUser;

import java.util.List;

public interface SysUserMapper {
    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    SysUser selectByName(String userName);

    /**
     * 查询用户的权限列表
     *
     * @param userId
     * @return
     */
    List<SysPermission> selectListByUser(Integer userId);

    Integer update(SysUser user);

    List<SysPermission> selectListByPath(String requestUrl);
}

