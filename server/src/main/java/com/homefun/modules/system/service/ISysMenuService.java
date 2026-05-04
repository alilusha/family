package com.homefun.modules.system.service;

import com.homefun.modules.system.domain.SysMenu;

import java.util.List;

public interface ISysMenuService {

    List<SysMenu> selectMenuList(SysMenu sysMenu);

    List<SysMenu> selectMenusByUserId(Long userId);

    List<SysMenu> selectMenuTreeAll();

    SysMenu selectMenuById(Long id);

    List<SysMenu> selectChildrenMenuById(Long id);

    void insertMenu(SysMenu menu);

    void updateMenu(SysMenu menu);

    int deleteMenuById(Long id);

    boolean hasChildByMenuId(Long id);

    boolean checkMenuExistRole(Long id);
}
