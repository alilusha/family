package com.homefun.modules.system.controller;

import com.homefun.common.core.domain.AjaxResult;
import com.homefun.common.core.domain.TableDataInfo;
import com.homefun.modules.system.domain.SysDictData;
import com.homefun.modules.system.domain.SysDictType;
import com.homefun.modules.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/dict")
public class SysDictController {

    @Autowired
    private ISysDictService dictService;

    @GetMapping("/type/list")
    public TableDataInfo typeList(SysDictType sysDictType) {
        List<SysDictType> list = dictService.selectDictTypeList(sysDictType);
        TableDataInfo dataTable = new TableDataInfo();
        dataTable.setRows(list);
        dataTable.setTotal(list.size());
        dataTable.setCode(200);
        dataTable.setMsg("查询成功");
        return dataTable;
    }

    @GetMapping("/data/list")
    public TableDataInfo dataList(SysDictData sysDictData) {
        List<SysDictData> list = dictService.selectDictDataList(sysDictData);
        TableDataInfo dataTable = new TableDataInfo();
        dataTable.setRows(list);
        dataTable.setTotal(list.size());
        dataTable.setCode(200);
        dataTable.setMsg("查询成功");
        return dataTable;
    }

    @GetMapping("/data/type/{dictType}")
    public AjaxResult getDataByType(@PathVariable String dictType) {
        return AjaxResult.success(dictService.selectDictDataByType(dictType));
    }

    @GetMapping("/type/{id}")
    public AjaxResult getTypeInfo(@PathVariable Long id) {
        return AjaxResult.success(dictService.selectDictTypeById(id));
    }

    @GetMapping("/data/{id}")
    public AjaxResult getDataInfo(@PathVariable Long id) {
        return AjaxResult.success(dictService.selectDictDataById(id));
    }

    @PostMapping("/type")
    public AjaxResult addType(@RequestBody SysDictType sysDictType) {
        dictService.insertDictType(sysDictType);
        return AjaxResult.success("新增字典类型成功");
    }

    @PutMapping("/type")
    public AjaxResult editType(@RequestBody SysDictType sysDictType) {
        dictService.updateDictType(sysDictType);
        return AjaxResult.success("修改字典类型成功");
    }

    @DeleteMapping("/type/{ids}")
    public AjaxResult removeType(@PathVariable Long[] ids) {
        for (Long id : ids) {
            dictService.deleteDictTypeById(id);
        }
        return AjaxResult.success("删除字典类型成功");
    }

    @PostMapping("/data")
    public AjaxResult addData(@RequestBody SysDictData sysDictData) {
        dictService.insertDictData(sysDictData);
        return AjaxResult.success("新增字典数据成功");
    }

    @PutMapping("/data")
    public AjaxResult editData(@RequestBody SysDictData sysDictData) {
        dictService.updateDictData(sysDictData);
        return AjaxResult.success("修改字典数据成功");
    }

    @DeleteMapping("/data/{ids}")
    public AjaxResult removeData(@PathVariable Long[] ids) {
        for (Long id : ids) {
            dictService.deleteDictDataById(id);
        }
        return AjaxResult.success("删除字典数据成功");
    }
}
