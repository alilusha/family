package com.homefun.modules.game.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homefun.modules.game.domain.GameWord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GameWordMapper extends BaseMapper<GameWord> {

    List<GameWord> selectWordList(GameWord word);

    GameWord selectWordById(@Param("id") Long id);

    int insertWord(GameWord word);

    int updateWord(GameWord word);

    int deleteWordById(@Param("id") Long id);

    List<GameWord> selectRandomWords(@Param("count") int count, @Param("difficulty") String difficulty);
}
