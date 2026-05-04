package com.homefun.modules.system.service;

import com.homefun.modules.system.domain.SysUser;

import java.util.List;

public interface ISysUserService {

    SysUser selectUserByUsername(String username);

    List<SysUser> selectUserList(SysUser sysUser);

    SysUser selectUserById(Long id);

    void insertUser(SysUser user);

    void updateUser(SysUser user);

    void deleteUserById(Long id);

    int deleteUserByIds(Long[] ids);

    List<Long> selectRoleListByUserId(Long userId);

    void insertUserAuth(Long userId, Long[] roleIds);
}
