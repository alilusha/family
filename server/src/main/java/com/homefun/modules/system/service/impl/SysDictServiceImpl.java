package com.homefun.modules.system.service.impl;

import com.homefun.modules.system.domain.SysDictData;
import com.homefun.modules.system.domain.SysDictType;
import com.homefun.modules.system.mapper.SysDictDataMapper;
import com.homefun.modules.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysDictServiceImpl implements ISysDictService {

    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Override
    public List<SysDictType> selectDictTypeList(SysDictType sysDictType) {
        return dictDataMapper.selectDictTypeList(sysDictType);
    }

    @Override
    public List<SysDictData> selectDictDataList(SysDictData sysDictData) {
        return dictDataMapper.selectDictDataList(sysDictData);
    }

    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        return dictDataMapper.selectDictDataByType(dictType);
    }

    @Override
    public SysDictType selectDictTypeById(Long id) {
        return dictDataMapper.selectDictTypeById(id);
    }

    @Override
    public SysDictData selectDictDataById(Long id) {
        return dictDataMapper.selectDictDataById(id);
    }

    @Override
    @Transactional
    public void insertDictType(SysDictType dictType) {
        dictDataMapper.insertDictType(dictType);
    }

    @Override
    @Transactional
    public void updateDictType(SysDictType dictType) {
        dictDataMapper.updateDictType(dictType);
    }

    @Override
    @Transactional
    public void deleteDictTypeById(Long id) {
        dictDataMapper.deleteDictTypeById(id);
    }

    @Override
    @Transactional
    public void insertDictData(SysDictData dictData) {
        dictDataMapper.insertDictData(dictData);
    }

    @Override
    @Transactional
    public void updateDictData(SysDictData dictData) {
        dictDataMapper.updateDictData(dictData);
    }

    @Override
    @Transactional
    public void deleteDictDataById(Long id) {
        dictDataMapper.deleteDictDataById(id);
    }

    @Override
    public void clearDictCache() {
    }
}
