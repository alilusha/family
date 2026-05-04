package com.homefun.modules.system.service.impl;

import com.homefun.modules.system.domain.SysMenu;
import com.homefun.modules.system.mapper.SysMenuMapper;
import com.homefun.modules.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public List<SysMenu> selectMenuList(SysMenu sysMenu) {
        return menuMapper.selectMenuList(sysMenu);
    }

    @Override
    public List<SysMenu> selectMenusByUserId(Long userId) {
        return menuMapper.selectMenusByUserId(userId);
    }

    @Override
    public List<SysMenu> selectMenuTreeAll() {
        List<SysMenu> menus = menuMapper.selectMenuList(new SysMenu());
        return buildMenuTree(menus);
    }

    @Override
    public SysMenu selectMenuById(Long id) {
        return menuMapper.selectMenuById(id);
    }

    @Override
    public List<SysMenu> selectChildrenMenuById(Long id) {
        return menuMapper.selectChildrenMenuById(id);
    }

    @Override
    @Transactional
    public void insertMenu(SysMenu menu) {
        menuMapper.insertMenu(menu);
    }

    @Override
    @Transactional
    public void updateMenu(SysMenu menu) {
        menuMapper.updateMenu(menu);
    }

    @Override
    @Transactional
    public int deleteMenuById(Long id) {
        return menuMapper.deleteMenuById(id);
    }

    @Override
    public boolean hasChildByMenuId(Long id) {
        List<SysMenu> children = menuMapper.selectChildrenMenuById(id);
        return children != null && !children.isEmpty();
    }

    @Override
    public boolean checkMenuExistRole(Long id) {
        return false;
    }

    private List<SysMenu> buildMenuTree(List<SysMenu> menus) {
        List<SysMenu> result = new ArrayList<>();
        for (SysMenu menu : menus) {
            if (menu.getParentId() == 0) {
                result.add(findChildren(menu, menus));
            }
        }
        return result;
    }

    private SysMenu findChildren(SysMenu parent, List<SysMenu> menus) {
        for (SysMenu menu : menus) {
            if (menu.getParentId().equals(parent.getId())) {
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(findChildren(menu, menus));
            }
        }
        return parent;
    }
}
