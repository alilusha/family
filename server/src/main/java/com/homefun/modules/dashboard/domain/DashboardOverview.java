package com.homefun.modules.dashboard.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class DashboardOverview implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userCount;
    private Long familyCount;
    private Long gameCount;
    private Long activityCount;

    private Double userGrowthRate;
    private Double familyGrowthRate;
    private Double gameGrowthRate;
    private Double activityGrowthRate;
}
