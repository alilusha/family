package com.homefun.modules.user.service.impl;

import com.homefun.modules.user.domain.AppUser;
import com.homefun.modules.user.mapper.AppUserMapper;
import com.homefun.modules.user.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppUserServiceImpl implements IAppUserService {

    @Autowired
    private AppUserMapper appUserMapper;

    @Override
    public List<AppUser> selectAppUserList(AppUser appUser) {
        return appUserMapper.selectAppUserList(appUser);
    }

    @Override
    public AppUser selectAppUserById(Long id) {
        return appUserMapper.selectAppUserById(id);
    }

    @Override
    public AppUser selectAppUserByPhone(String phone) {
        return appUserMapper.selectAppUserByPhone(phone);
    }

    @Override
    @Transactional
    public void insertAppUser(AppUser appUser) {
        if (appUser.getStatus() == null) {
            appUser.setStatus("0");
        }
        if (appUser.getTotalScore() == null) {
            appUser.setTotalScore(0);
        }
        if (appUser.getGameCount() == null) {
            appUser.setGameCount(0);
        }
        if (appUser.getActivityCount() == null) {
            appUser.setActivityCount(0);
        }
        appUserMapper.insertAppUser(appUser);
    }

    @Override
    @Transactional
    public void updateAppUser(AppUser appUser) {
        appUserMapper.updateAppUser(appUser);
    }

    @Override
    @Transactional
    public void deleteAppUserById(Long id) {
        appUserMapper.deleteAppUserById(id);
    }

    @Override
    @Transactional
    public int deleteAppUserByIds(Long[] ids) {
        int count = 0;
        for (Long id : ids) {
            appUserMapper.deleteAppUserById(id);
            count++;
        }
        return count;
    }

    @Override
    public List<AppUser> selectUserListByFamilyId(Long familyId) {
        return appUserMapper.selectUserListByFamilyId(familyId);
    }

    @Override
    @Transactional
    public void updateUserScore(Long userId, int score) {
        AppUser user = appUserMapper.selectAppUserById(userId);
        if (user != null) {
            user.setTotalScore(user.getTotalScore() + score);
            user.setGameCount(user.getGameCount() + 1);
            appUserMapper.updateAppUser(user);
        }
    }
}
