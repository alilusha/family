package com.homefun.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.homefun.modules.system.domain.SysUser;
import com.homefun.modules.system.mapper.SysUserMapper;
import com.homefun.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUser selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public List<SysUser> selectUserList(SysUser sysUser) {
        return userMapper.selectUserList(sysUser);
    }

    @Override
    public SysUser selectUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    @Transactional
    public void insertUser(SysUser user) {
        userMapper.insertUser(user);
    }

    @Override
    @Transactional
    public void updateUser(SysUser user) {
        userMapper.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userMapper.deleteUserById(id);
    }

    @Override
    @Transactional
    public int deleteUserByIds(Long[] ids) {
        int count = 0;
        for (Long id : ids) {
            userMapper.deleteUserById(id);
            count++;
        }
        return count;
    }

    @Override
    public List<Long> selectRoleListByUserId(Long userId) {
        return userMapper.selectRoleListByUserId(userId);
    }

    @Override
    @Transactional
    public void insertUserAuth(Long userId, Long[] roleIds) {
    }
}
