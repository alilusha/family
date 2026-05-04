package com.homefun.modules.family.service;

import com.homefun.modules.family.domain.Family;

import java.util.List;

public interface IFamilyService {

    List<Family> selectFamilyList(Family family);

    Family selectFamilyById(Long id);

    Family selectFamilyByCode(String code);

    void insertFamily(Family family);

    void updateFamily(Family family);

    void deleteFamilyById(Long id);

    int deleteFamilyByIds(Long[] ids);

    String generateInviteCode();

    void joinFamily(Long userId, String inviteCode);

    void leaveFamily(Long userId);
}
