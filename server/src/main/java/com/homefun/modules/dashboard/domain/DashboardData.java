package com.homefun.modules.dashboard.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DashboardData implements Serializable {
    private static final long serialVersionUID = 1L;

    private DashboardOverview overview;
    private List<int[]> userTrend;
    private List<int[]> gameTrend;
    private List<HotFamily> hotFamilies;
    private List<RecentActivity> recentActivities;

    @Data
    public static class HotFamily implements Serializable {
        private String name;
        private Integer gameCount;
    }

    @Data
    public static class RecentActivity implements Serializable {
        private String title;
        private String familyName;
        private String activityTime;
        private Integer participantCount;
        private String status;
    }
}
