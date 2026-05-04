package com.homefun.modules.game.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homefun.modules.game.domain.GameQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GameQuestionMapper extends BaseMapper<GameQuestion> {

    List<GameQuestion> selectQuestionList(GameQuestion question);

    GameQuestion selectQuestionById(@Param("id") Long id);

    int insertQuestion(GameQuestion question);

    int updateQuestion(GameQuestion question);

    int deleteQuestionById(@Param("id") Long id);

    List<GameQuestion> selectRandomQuestions(@Param("count") int count, @Param("difficulty") String difficulty);
}
