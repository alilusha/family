package com.homefun.modules.family.service.impl;

import com.homefun.modules.family.domain.Family;
import com.homefun.modules.family.mapper.FamilyMapper;
import com.homefun.modules.family.service.IFamilyService;
import com.homefun.modules.user.domain.AppUser;
import com.homefun.modules.user.mapper.AppUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class FamilyServiceImpl implements IFamilyService {

    @Autowired
    private FamilyMapper familyMapper;

    @Autowired
    private AppUserMapper appUserMapper;

    private static final String CODE_CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final Random random = new Random();

    @Override
    public List<Family> selectFamilyList(Family family) {
        return familyMapper.selectFamilyList(family);
    }

    @Override
    public Family selectFamilyById(Long id) {
        return familyMapper.selectFamilyById(id);
    }

    @Override
    public Family selectFamilyByCode(String code) {
        return familyMapper.selectFamilyByCode(code);
    }

    @Override
    @Transactional
    public void insertFamily(Family family) {
        if (family.getCode() == null || family.getCode().isEmpty()) {
            family.setCode(generateInviteCode());
        }
        if (family.getMemberCount() == null) {
            family.setMemberCount(1);
        }
        if (family.getStatus() == null) {
            family.setStatus("0");
        }
        familyMapper.insertFamily(family);
    }

    @Override
    @Transactional
    public void updateFamily(Family family) {
        familyMapper.updateFamily(family);
    }

    @Override
    @Transactional
    public void deleteFamilyById(Long id) {
        familyMapper.deleteFamilyById(id);
    }

    @Override
    @Transactional
    public int deleteFamilyByIds(Long[] ids) {
        int count = 0;
        for (Long id : ids) {
            familyMapper.deleteFamilyById(id);
            count++;
        }
        return count;
    }

    @Override
    public String generateInviteCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(CODE_CHARS.charAt(random.nextInt(CODE_CHARS.length())));
        }
        if (familyMapper.selectFamilyByCode(code.toString()) != null) {
            return generateInviteCode();
        }
        return code.toString();
    }

    @Override
    @Transactional
    public void joinFamily(Long userId, String inviteCode) {
        Family family = familyMapper.selectFamilyByCode(inviteCode);
        if (family == null) {
            throw new RuntimeException("邀请码不存在");
        }
        AppUser user = appUserMapper.selectAppUserById(userId);
        if (user != null) {
            user.setFamilyId(family.getId());
            user.setRoleInFamily("家庭成员");
            appUserMapper.updateAppUser(user);

            family.setMemberCount(family.getMemberCount() + 1);
            familyMapper.updateFamily(family);
        }
    }

    @Override
    @Transactional
    public void leaveFamily(Long userId) {
        AppUser user = appUserMapper.selectAppUserById(userId);
        if (user != null && user.getFamilyId() != null) {
            Family family = familyMapper.selectFamilyById(user.getFamilyId());
            if (family != null) {
                family.setMemberCount(Math.max(0, family.getMemberCount() - 1));
                familyMapper.updateFamily(family);
            }
            user.setFamilyId(null);
            user.setRoleInFamily(null);
            appUserMapper.updateAppUser(user);
        }
    }
}
