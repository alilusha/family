package com.homefun.modules.media.controller;

import com.homefun.common.core.domain.AjaxResult;
import com.homefun.common.core.domain.TableDataInfo;
import com.homefun.modules.media.domain.MediaContent;
import com.homefun.modules.media.service.IMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media/content")
public class MediaContentController {

    @Autowired
    private IMediaService mediaService;

    @GetMapping("/list")
    public TableDataInfo list(MediaContent content) {
        List<MediaContent> list = mediaService.selectContentList(content);
        TableDataInfo dataTable = new TableDataInfo();
        dataTable.setRows(list);
        dataTable.setTotal(list.size());
        dataTable.setCode(200);
        dataTable.setMsg("查询成功");
        return dataTable;
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(mediaService.selectContentById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody MediaContent content) {
        mediaService.insertContent(content);
        return AjaxResult.success("新增内容成功");
    }

    @PutMapping
    public AjaxResult edit(@RequestBody MediaContent content) {
        mediaService.updateContent(content);
        return AjaxResult.success("修改内容成功");
    }

    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        mediaService.deleteContentByIds(ids);
        return AjaxResult.success("删除内容成功");
    }

    @GetMapping("/category/{categoryId}")
    public AjaxResult getByCategory(@PathVariable Long categoryId) {
        List<MediaContent> list = mediaService.selectContentByCategoryId(categoryId);
        return AjaxResult.success(list);
    }

    @PutMapping("/view/{id}")
    public AjaxResult view(@PathVariable Long id) {
        mediaService.incrementViewCount(id);
        return AjaxResult.success();
    }
}
