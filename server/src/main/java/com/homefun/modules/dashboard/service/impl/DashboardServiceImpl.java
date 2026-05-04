package com.homefun.modules.dashboard.service.impl;

import com.homefun.modules.activity.domain.Activity;
import com.homefun.modules.activity.mapper.ActivityMapper;
import com.homefun.modules.dashboard.domain.DashboardData;
import com.homefun.modules.dashboard.domain.DashboardOverview;
import com.homefun.modules.dashboard.mapper.DashboardMapper;
import com.homefun.modules.dashboard.service.IDashboardService;
import com.homefun.modules.family.domain.Family;
import com.homefun.modules.family.mapper.FamilyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardServiceImpl implements IDashboardService {

    @Autowired
    private DashboardMapper dashboardMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private FamilyMapper familyMapper;

    @Override
    public DashboardData getDashboardData() {
        DashboardData data = new DashboardData();
        data.setOverview(getOverview());
        data.setUserTrend(getUserTrend());
        data.setGameTrend(getGameTrend());
        data.setHotFamilies(getHotFamilies());
        data.setRecentActivities(getRecentActivities());
        return data;
    }

    @Override
    public DashboardOverview getOverview() {
        DashboardOverview overview = new DashboardOverview();

        Long userCount = dashboardMapper.selectUserCount();
        Long familyCount = dashboardMapper.selectFamilyCount();
        Long gameCount = dashboardMapper.selectGameCount();
        Long activityCount = dashboardMapper.selectActivityCount();

        Long todayUser = dashboardMapper.selectTodayUserCount();
        Long todayFamily = dashboardMapper.selectTodayFamilyCount();
        Long todayGame = dashboardMapper.selectTodayGameCount();
        Long todayActivity = dashboardMapper.selectTodayActivityCount();

        overview.setUserCount(userCount);
        overview.setFamilyCount(familyCount);
        overview.setGameCount(gameCount);
        overview.setActivityCount(activityCount);

        overview.setUserGrowthRate(userCount > 0 ? (double) todayUser / userCount * 100 : 0);
        overview.setFamilyGrowthRate(familyCount > 0 ? (double) todayFamily / familyCount * 100 : 0);
        overview.setGameGrowthRate(gameCount > 0 ? (double) todayGame / gameCount * 100 : 0);
        overview.setActivityGrowthRate(activityCount > 0 ? (double) todayActivity / activityCount * 100 : 0);

        return overview;
    }

    @Override
    public List<int[]> getUserTrend() {
        List<int[]> trend = new ArrayList<>();
        trend.add(new int[]{120, 200, 150, 80, 70, 230, 180});
        return trend;
    }

    @Override
    public List<int[]> getGameTrend() {
        List<int[]> trend = new ArrayList<>();
        trend.add(new int[]{120, 200, 150, 80, 70, 400, 380});
        trend.add(new int[]{100, 180, 130, 70, 60, 350, 320});
        return trend;
    }

    @Override
    public List<DashboardData.HotFamily> getHotFamilies() {
        List<DashboardData.HotFamily> hotFamilies = new ArrayList<>();

        List<Family> families = familyMapper.selectFamilyList(new Family());
        for (int i = 0; i < Math.min(6, families.size()); i++) {
            Family family = families.get(i);
            DashboardData.HotFamily hf = new DashboardData.HotFamily();
            hf.setName(family.getName());
            hf.setGameCount(family.getGameCount() != null ? family.getGameCount() : 0);
            hotFamilies.add(hf);
        }

        if (hotFamilies.isEmpty()) {
            hotFamilies.add(createMockHotFamily("快乐家庭1号", 328));
            hotFamilies.add(createMockHotFamily("幸福家庭3号", 295));
            hotFamilies.add(createMockHotFamily("温馨家庭5号", 256));
            hotFamilies.add(createMockHotFamily("开心家庭2号", 234));
            hotFamilies.add(createMockHotFamily("美满家庭4号", 201));
            hotFamilies.add(createMockHotFamily("欢乐家庭6号", 185));
        }

        return hotFamilies;
    }

    private DashboardData.HotFamily createMockHotFamily(String name, int gameCount) {
        DashboardData.HotFamily hf = new DashboardData.HotFamily();
        hf.setName(name);
        hf.setGameCount(gameCount);
        return hf;
    }

    @Override
    public List<DashboardData.RecentActivity> getRecentActivities() {
        List<DashboardData.RecentActivity> recentActivities = new ArrayList<>();

        List<Activity> activities = activityMapper.selectActivityList(new Activity());
        for (int i = 0; i < Math.min(5, activities.size()); i++) {
            Activity activity = activities.get(i);
            DashboardData.RecentActivity ra = new DashboardData.RecentActivity();
            ra.setTitle(activity.getTitle());
            ra.setFamilyName(activity.getFamilyName());
            ra.setActivityTime(activity.getActivityTime() != null ? activity.getActivityTime().toString() : "");
            ra.setParticipantCount(activity.getParticipantCount() != null ? activity.getParticipantCount() : 0);
            ra.setStatus(activity.getStatus());
            recentActivities.add(ra);
        }

        if (recentActivities.isEmpty()) {
            recentActivities.add(createMockActivity("家庭电影之夜", "快乐家庭1号", "2024-01-15 20:00", 4, "1"));
            recentActivities.add(createMockActivity("周末游戏日", "幸福家庭3号", "2024-01-14 14:00", 3, "2"));
            recentActivities.add(createMockActivity("亲子手工活动", "温馨家庭5号", "2024-01-16 10:00", 5, "0"));
            recentActivities.add(createMockActivity("家庭知识竞赛", "开心家庭2号", "2024-01-13 19:00", 4, "2"));
            recentActivities.add(createMockActivity("户外野餐活动", "美满家庭4号", "2024-01-17 11:00", 6, "0"));
        }

        return recentActivities;
    }

    private DashboardData.RecentActivity createMockActivity(String title, String familyName, String time, int participants, String status) {
        DashboardData.RecentActivity ra = new DashboardData.RecentActivity();
        ra.setTitle(title);
        ra.setFamilyName(familyName);
        ra.setActivityTime(time);
        ra.setParticipantCount(participants);
        ra.setStatus(status);
        return ra;
    }
}
