package com.homefun.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homefun.modules.system.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> selectRoleList(SysRole sysRole);

    List<SysRole> selectRolesByUserId(@Param("userId") Long userId);

    SysRole selectRoleById(@Param("id") Long id);

    int insertRole(SysRole sysRole);

    int updateRole(SysRole sysRole);

    int deleteRoleById(@Param("id") Long id);

    int countRoleByMenuId(@Param("menuId") Long menuId);
}
