package com.homefun.modules.dashboard.controller;

import com.homefun.common.core.domain.AjaxResult;
import com.homefun.modules.dashboard.service.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private IDashboardService dashboardService;

    @GetMapping("/overview")
    public AjaxResult getOverview() {
        return AjaxResult.success(dashboardService.getOverview());
    }

    @GetMapping("/userTrend")
    public AjaxResult getUserTrend() {
        return AjaxResult.success(dashboardService.getUserTrend());
    }

    @GetMapping("/gameTrend")
    public AjaxResult getGameTrend() {
        return AjaxResult.success(dashboardService.getGameTrend());
    }

    @GetMapping("/hotFamilies")
    public AjaxResult getHotFamilies() {
        return AjaxResult.success(dashboardService.getHotFamilies());
    }

    @GetMapping("/recentActivities")
    public AjaxResult getRecentActivities() {
        return AjaxResult.success(dashboardService.getRecentActivities());
    }

    @GetMapping("/data")
    public AjaxResult getDashboardData() {
        return AjaxResult.success(dashboardService.getDashboardData());
    }
}
