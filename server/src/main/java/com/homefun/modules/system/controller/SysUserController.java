package com.homefun.modules.system.controller;

import com.homefun.common.core.domain.AjaxResult;
import com.homefun.common.core.domain.TableDataInfo;
import com.homefun.modules.system.domain.SysUser;
import com.homefun.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/user")
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    @GetMapping("/list")
    public TableDataInfo list(SysUser sysUser) {
        List<SysUser> list = userService.selectUserList(sysUser);
        TableDataInfo dataTable = new TableDataInfo();
        dataTable.setRows(list);
        dataTable.setTotal(list.size());
        dataTable.setCode(200);
        dataTable.setMsg("查询成功");
        return dataTable;
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(userService.selectUserById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody SysUser sysUser) {
        userService.insertUser(sysUser);
        return AjaxResult.success("新增用户成功");
    }

    @PutMapping
    public AjaxResult edit(@RequestBody SysUser sysUser) {
        userService.updateUser(sysUser);
        return AjaxResult.success("修改用户成功");
    }

    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        userService.deleteUserByIds(ids);
        return AjaxResult.success("删除用户成功");
    }

    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser sysUser) {
        userService.updateUser(sysUser);
        return AjaxResult.success("状态修改成功");
    }

    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUser sysUser) {
        userService.updateUser(sysUser);
        return AjaxResult.success("重置密码成功");
    }
}
