package com.homefun.modules.family.controller;

import com.homefun.common.core.domain.AjaxResult;
import com.homefun.common.core.domain.TableDataInfo;
import com.homefun.modules.family.domain.Family;
import com.homefun.modules.family.service.IFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/family")
public class FamilyController {

    @Autowired
    private IFamilyService familyService;

    @GetMapping("/list")
    public TableDataInfo list(Family family) {
        List<Family> list = familyService.selectFamilyList(family);
        TableDataInfo dataTable = new TableDataInfo();
        dataTable.setRows(list);
        dataTable.setTotal(list.size());
        dataTable.setCode(200);
        dataTable.setMsg("查询成功");
        return dataTable;
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(familyService.selectFamilyById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody Family family) {
        familyService.insertFamily(family);
        return AjaxResult.success("新增家庭成功");
    }

    @PutMapping
    public AjaxResult edit(@RequestBody Family family) {
        familyService.updateFamily(family);
        return AjaxResult.success("修改家庭成功");
    }

    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        familyService.deleteFamilyByIds(ids);
        return AjaxResult.success("删除家庭成功");
    }

    @GetMapping("/code/{code}")
    public AjaxResult getByCode(@PathVariable String code) {
        return AjaxResult.success(familyService.selectFamilyByCode(code));
    }

    @GetMapping("/generate/code")
    public AjaxResult generateCode() {
        return AjaxResult.success(familyService.generateInviteCode());
    }
}
