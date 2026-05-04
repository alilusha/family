package com.homefun.modules.activity.controller;

import com.homefun.common.core.domain.AjaxResult;
import com.homefun.common.core.domain.TableDataInfo;
import com.homefun.modules.activity.domain.Activity;
import com.homefun.modules.activity.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private IActivityService activityService;

    @GetMapping("/list")
    public TableDataInfo list(Activity activity) {
        List<Activity> list = activityService.selectActivityList(activity);
        TableDataInfo dataTable = new TableDataInfo();
        dataTable.setRows(list);
        dataTable.setTotal(list.size());
        dataTable.setCode(200);
        dataTable.setMsg("查询成功");
        return dataTable;
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(activityService.selectActivityById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody Activity activity) {
        activityService.insertActivity(activity);
        return AjaxResult.success("新增活动成功");
    }

    @PutMapping
    public AjaxResult edit(@RequestBody Activity activity) {
        activityService.updateActivity(activity);
        return AjaxResult.success("修改活动成功");
    }

    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        activityService.deleteActivityByIds(ids);
        return AjaxResult.success("删除活动成功");
    }

    @GetMapping("/family/{familyId}")
    public AjaxResult getByFamily(@PathVariable Long familyId) {
        List<Activity> list = activityService.selectActivityByFamilyId(familyId);
        return AjaxResult.success(list);
    }

    @GetMapping("/upcoming")
    public AjaxResult getUpcoming(@RequestParam(defaultValue = "5") int limit) {
        List<Activity> list = activityService.selectUpcomingActivities(limit);
        return AjaxResult.success(list);
    }

    @PostMapping("/join/{id}")
    public AjaxResult join(@PathVariable Long id, @RequestParam Long userId) {
        activityService.joinActivity(id, userId);
        return AjaxResult.success("参加活动成功");
    }

    @PostMapping("/leave/{id}")
    public AjaxResult leave(@PathVariable Long id, @RequestParam Long userId) {
        activityService.leaveActivity(id, userId);
        return AjaxResult.success("退出活动成功");
    }
}
