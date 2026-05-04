package com.homefun.modules.activity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homefun.modules.activity.domain.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {

    List<Activity> selectActivityList(Activity activity);

    Activity selectActivityById(@Param("id") Long id);

    int insertActivity(Activity activity);

    int updateActivity(Activity activity);

    int deleteActivityById(@Param("id") Long id);

    List<Activity> selectActivityByFamilyId(@Param("familyId") Long familyId);

    List<Activity> selectUpcomingActivities(@Param("limit") int limit);
}
