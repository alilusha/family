package com.homefun.modules.activity.service.impl;

import com.homefun.modules.activity.domain.Activity;
import com.homefun.modules.activity.mapper.ActivityMapper;
import com.homefun.modules.activity.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> selectActivityList(Activity activity) {
        return activityMapper.selectActivityList(activity);
    }

    @Override
    public Activity selectActivityById(Long id) {
        return activityMapper.selectActivityById(id);
    }

    @Override
    @Transactional
    public void insertActivity(Activity activity) {
        if (activity.getStatus() == null) {
            activity.setStatus("0");
        }
        if (activity.getParticipantCount() == null) {
            activity.setParticipantCount(0);
        }
        if (activity.getMaxParticipants() == null) {
            activity.setMaxParticipants(10);
        }
        activityMapper.insertActivity(activity);
    }

    @Override
    @Transactional
    public void updateActivity(Activity activity) {
        activityMapper.updateActivity(activity);
    }

    @Override
    @Transactional
    public void deleteActivityById(Long id) {
        activityMapper.deleteActivityById(id);
    }

    @Override
    @Transactional
    public int deleteActivityByIds(Long[] ids) {
        int count = 0;
        for (Long id : ids) {
            activityMapper.deleteActivityById(id);
            count++;
        }
        return count;
    }

    @Override
    public List<Activity> selectActivityByFamilyId(Long familyId) {
        return activityMapper.selectActivityByFamilyId(familyId);
    }

    @Override
    public List<Activity> selectUpcomingActivities(int limit) {
        return activityMapper.selectUpcomingActivities(limit);
    }

    @Override
    @Transactional
    public void joinActivity(Long activityId, Long userId) {
        Activity activity = activityMapper.selectActivityById(activityId);
        if (activity == null) {
            throw new RuntimeException("活动不存在");
        }
        if (activity.getParticipantCount() >= activity.getMaxParticipants()) {
            throw new RuntimeException("活动人数已满");
        }

        String participantIds = activity.getParticipantIds();
        List<Long> ids = participantIds != null && !participantIds.isEmpty()
                ? Arrays.stream(participantIds.split(",")).map(Long::parseLong).collect(Collectors.toList())
                : Collections.emptyList();

        if (!ids.contains(userId)) {
            ids.add(userId);
            activity.setParticipantIds(String.join(",", ids.stream().map(String::valueOf).collect(Collectors.toList())));
            activity.setParticipantCount(ids.size());
            activityMapper.updateActivity(activity);
        }
    }

    @Override
    @Transactional
    public void leaveActivity(Long activityId, Long userId) {
        Activity activity = activityMapper.selectActivityById(activityId);
        if (activity == null) {
            throw new RuntimeException("活动不存在");
        }

        String participantIds = activity.getParticipantIds();
        if (participantIds != null && !participantIds.isEmpty()) {
            List<Long> ids = Arrays.stream(participantIds.split(",")).map(Long::parseLong).collect(Collectors.toList());
            ids.remove(userId);
            activity.setParticipantIds(String.join(",", ids.stream().map(String::valueOf).collect(Collectors.toList())));
            activity.setParticipantCount(ids.size());
            activityMapper.updateActivity(activity);
        }
    }
}
