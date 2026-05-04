package com.homefun.modules.activity.service;

import com.homefun.modules.activity.domain.Activity;

import java.util.List;

public interface IActivityService {

    List<Activity> selectActivityList(Activity activity);

    Activity selectActivityById(Long id);

    void insertActivity(Activity activity);

    void updateActivity(Activity activity);

    void deleteActivityById(Long id);

    int deleteActivityByIds(Long[] ids);

    List<Activity> selectActivityByFamilyId(Long familyId);

    List<Activity> selectUpcomingActivities(int limit);

    void joinActivity(Long activityId, Long userId);

    void leaveActivity(Long activityId, Long userId);
}
