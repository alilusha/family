package com.homefun.modules.dashboard.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DashboardMapper {

    @Select("SELECT COUNT(*) FROM app_user WHERE del_flag = '0'")
    Long selectUserCount();

    @Select("SELECT COUNT(*) FROM family WHERE del_flag = '0'")
    Long selectFamilyCount();

    @Select("SELECT COUNT(*) FROM game_record")
    Long selectGameCount();

    @Select("SELECT COUNT(*) FROM activity WHERE del_flag = '0'")
    Long selectActivityCount();

    @Select("SELECT COUNT(*) FROM app_user WHERE DATE(create_time) = CURDATE() AND del_flag = '0'")
    Long selectTodayUserCount();

    @Select("SELECT COUNT(*) FROM family WHERE DATE(create_time) = CURDATE() AND del_flag = '0'")
    Long selectTodayFamilyCount();

    @Select("SELECT COUNT(*) FROM game_record WHERE DATE(create_time) = CURDATE()")
    Long selectTodayGameCount();

    @Select("SELECT COUNT(*) FROM activity WHERE DATE(create_time) = CURDATE() AND del_flag = '0'")
    Long selectTodayActivityCount();
}
