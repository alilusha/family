package com.homefun.modules.system.controller;

import com.homefun.common.core.domain.AjaxResult;
import com.homefun.common.core.domain.TableDataInfo;
import com.homefun.modules.system.domain.SysMenu;
import com.homefun.modules.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService menuService;

    @GetMapping("/list")
    public TableDataInfo list(SysMenu sysMenu) {
        List<SysMenu> list = menuService.selectMenuList(sysMenu);
        TableDataInfo dataTable = new TableDataInfo();
        dataTable.setRows(list);
        dataTable.setTotal(list.size());
        dataTable.setCode(200);
        dataTable.setMsg("查询成功");
        return dataTable;
    }

    @GetMapping("/treeselect")
    public AjaxResult treeselect(SysMenu sysMenu) {
        List<SysMenu> menus = menuService.selectMenuList(sysMenu);
        return AjaxResult.success(menuService.buildMenuTree(menus));
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(menuService.selectMenuById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody SysMenu sysMenu) {
        menuService.insertMenu(sysMenu);
        return AjaxResult.success("新增菜单成功");
    }

    @PutMapping
    public AjaxResult edit(@RequestBody SysMenu sysMenu) {
        menuService.updateMenu(sysMenu);
        return AjaxResult.success("修改菜单成功");
    }

    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        if (menuService.hasChildByMenuId(id)) {
            return AjaxResult.error("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(id)) {
            return AjaxResult.error("菜单已分配,不允许删除");
        }
        menuService.deleteMenuById(id);
        return AjaxResult.success("删除菜单成功");
    }
}
