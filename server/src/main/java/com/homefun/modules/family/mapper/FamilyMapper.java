package com.homefun.modules.family.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homefun.modules.family.domain.Family;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FamilyMapper extends BaseMapper<Family> {

    List<Family> selectFamilyList(Family family);

    Family selectFamilyById(@Param("id") Long id);

    Family selectFamilyByCode(@Param("code") String code);

    int insertFamily(Family family);

    int updateFamily(Family family);

    int deleteFamilyById(@Param("id") Long id);

    int countUserByFamilyId(@Param("familyId") Long familyId);
}
