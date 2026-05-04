package com.homefun.modules.dashboard.service;

import com.homefun.modules.dashboard.domain.DashboardData;

public interface IDashboardService {

    DashboardData getDashboardData();

    Object getOverview();

    Object getUserTrend();

    Object getGameTrend();

    Object getHotFamilies();

    Object getRecentActivities();
}
