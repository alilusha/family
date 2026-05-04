package com.homefun.modules.user.service;

import com.homefun.modules.user.domain.AppUser;

import java.util.List;

public interface IAppUserService {

    List<AppUser> selectAppUserList(AppUser appUser);

    AppUser selectAppUserById(Long id);

    AppUser selectAppUserByPhone(String phone);

    void insertAppUser(AppUser appUser);

    void updateAppUser(AppUser appUser);

    void deleteAppUserById(Long id);

    int deleteAppUserByIds(Long[] ids);

    List<AppUser> selectUserListByFamilyId(Long familyId);

    void updateUserScore(Long userId, int score);
}
