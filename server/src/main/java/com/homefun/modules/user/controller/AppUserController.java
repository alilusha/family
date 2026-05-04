package com.homefun.modules.user.controller;

import com.homefun.common.core.domain.AjaxResult;
import com.homefun.common.core.domain.TableDataInfo;
import com.homefun.modules.user.domain.AppUser;
import com.homefun.modules.user.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/app")
public class AppUserController {

    @Autowired
    private IAppUserService appUserService;

    @GetMapping("/list")
    public TableDataInfo list(AppUser appUser) {
        startPage();
        List<AppUser> list = appUserService.selectAppUserList(appUser);
        return getDataTable(list);
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(appUserService.selectAppUserById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody AppUser appUser) {
        appUserService.insertAppUser(appUser);
        return AjaxResult.success("新增用户成功");
    }

    @PutMapping
    public AjaxResult edit(@RequestBody AppUser appUser) {
        appUserService.updateAppUser(appUser);
        return AjaxResult.success("修改用户成功");
    }

    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        appUserService.deleteAppUserByIds(ids);
        return AjaxResult.success("删除用户成功");
    }

    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody AppUser appUser) {
        appUserService.updateAppUser(appUser);
        return AjaxResult.success("状态修改成功");
    }

    @GetMapping("/family/{familyId}")
    public AjaxResult getUsersByFamily(@PathVariable Long familyId) {
        List<AppUser> list = appUserService.selectUserListByFamilyId(familyId);
        return AjaxResult.success(list);
    }

    private void startPage() {
    }

    private TableDataInfo getDataTable(List<?> list) {
        TableDataInfo dataTable = new TableDataInfo();
        dataTable.setRows(list);
        dataTable.setTotal(list.size());
        dataTable.setCode(200);
        dataTable.setMsg("查询成功");
        return dataTable;
    }
}
