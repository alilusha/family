package com.homefun.modules.media.controller;

import com.homefun.common.core.domain.AjaxResult;
import com.homefun.common.core.domain.TableDataInfo;
import com.homefun.modules.media.domain.MediaCategory;
import com.homefun.modules.media.service.IMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media/category")
public class MediaCategoryController {

    @Autowired
    private IMediaService mediaService;

    @GetMapping("/list")
    public TableDataInfo list(MediaCategory category) {
        List<MediaCategory> list = mediaService.selectCategoryList(category);
        TableDataInfo dataTable = new TableDataInfo();
        dataTable.setRows(list);
        dataTable.setTotal(list.size());
        dataTable.setCode(200);
        dataTable.setMsg("查询成功");
        return dataTable;
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(mediaService.selectCategoryById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody MediaCategory category) {
        mediaService.insertCategory(category);
        return AjaxResult.success("新增分类成功");
    }

    @PutMapping
    public AjaxResult edit(@RequestBody MediaCategory category) {
        mediaService.updateCategory(category);
        return AjaxResult.success("修改分类成功");
    }

    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        mediaService.deleteCategoryByIds(ids);
        return AjaxResult.success("删除分类成功");
    }
}
