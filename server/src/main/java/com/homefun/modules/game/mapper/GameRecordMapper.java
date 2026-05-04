package com.homefun.modules.game.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homefun.modules.game.domain.GameRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GameRecordMapper extends BaseMapper<GameRecord> {

    List<GameRecord> selectRecordList(GameRecord record);

    GameRecord selectRecordById(@Param("id") Long id);

    int insertRecord(GameRecord record);

    List<GameRecord> selectRecordsByFamilyId(@Param("familyId") Long familyId);
}
