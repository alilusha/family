package com.homefun.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homefun.modules.system.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectUserByUsername(@Param("username") String username);

    List<SysUser> selectUserList(SysUser sysUser);

    List<Long> selectRoleListByUserId(@Param("userId") Long userId);

    int insertUser(SysUser sysUser);

    int updateUser(SysUser sysUser);

    int deleteUserById(@Param("id") Long id);

    int countUserByRoleId(@Param("roleId") Long roleId);
}
