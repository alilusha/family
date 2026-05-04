package com.homefun.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homefun.modules.system.domain.SysDictData;
import com.homefun.modules.system.domain.SysDictType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysDictDataMapper extends BaseMapper<SysDictData> {

    List<SysDictData> selectDictDataList(SysDictData sysDictData);

    List<SysDictData> selectDictDataByType(@Param("dictType") String dictType);

    SysDictData selectDictDataById(@Param("id") Long id);

    int insertDictData(SysDictData sysDictData);

    int updateDictData(SysDictData sysDictData);

    int deleteDictDataById(@Param("id") Long id);

    int countDictDataByType(@Param("dictType") String dictType);

    List<SysDictType> selectDictTypeList(SysDictType sysDictType);

    SysDictType selectDictTypeById(@Param("id") Long id);

    SysDictType selectDictTypeByType(@Param("dictType") String dictType);

    int insertDictType(SysDictType sysDictType);

    int updateDictType(SysDictType sysDictType);

    int deleteDictTypeById(@Param("id") Long id);
}
