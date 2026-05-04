package com.homefun.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homefun.modules.system.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectMenuList(SysMenu sysMenu);

    List<SysMenu> selectMenusByUserId(@Param("userId") Long userId);

    SysMenu selectMenuById(@Param("id") Long id);

    List<SysMenu> selectChildrenMenuById(@Param("id") Long id);

    int insertMenu(SysMenu sysMenu);

    int updateMenu(SysMenu sysMenu);

    int deleteMenuById(@Param("id") Long id);

    List<Long> selectMenuListByRoleId(@Param("roleId") Long roleId);
}
