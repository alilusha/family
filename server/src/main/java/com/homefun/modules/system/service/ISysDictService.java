package com.homefun.modules.system.service;

import com.homefun.modules.system.domain.SysDictData;
import com.homefun.modules.system.domain.SysDictType;

import java.util.List;

public interface ISysDictService {

    List<SysDictType> selectDictTypeList(SysDictType sysDictType);

    List<SysDictData> selectDictDataList(SysDictData sysDictData);

    List<SysDictData> selectDictDataByType(String dictType);

    SysDictType selectDictTypeById(Long id);

    SysDictData selectDictDataById(Long id);

    void insertDictType(SysDictType dictType);

    void updateDictType(SysDictType dictType);

    void deleteDictTypeById(Long id);

    void insertDictData(SysDictData dictData);

    void updateDictData(SysDictData dictData);

    void deleteDictDataById(Long id);

    void clearDictCache();
}
