package com.homefun.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homefun.modules.user.domain.AppUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppUserMapper extends BaseMapper<AppUser> {

    List<AppUser> selectAppUserList(AppUser appUser);

    AppUser selectAppUserById(@Param("id") Long id);

    AppUser selectAppUserByPhone(@Param("phone") String phone);

    int insertAppUser(AppUser appUser);

    int updateAppUser(AppUser appUser);

    int deleteAppUserById(@Param("id") Long id);

    int countByFamilyId(@Param("familyId") Long familyId);

    List<AppUser> selectUserListByFamilyId(@Param("familyId") Long familyId);
}
