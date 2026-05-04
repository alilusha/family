package com.homefun.modules.game.service;

import com.homefun.modules.game.domain.GameQuestion;
import com.homefun.modules.game.domain.GameRecord;
import com.homefun.modules.game.domain.GameWord;

import java.util.List;

public interface IGameService {

    List<GameQuestion> selectQuestionList(GameQuestion question);

    GameQuestion selectQuestionById(Long id);

    void insertQuestion(GameQuestion question);

    void updateQuestion(GameQuestion question);

    void deleteQuestionById(Long id);

    int deleteQuestionByIds(Long[] ids);

    List<GameQuestion> selectRandomQuestions(int count, String difficulty);

    List<GameWord> selectWordList(GameWord word);

    GameWord selectWordById(Long id);

    void insertWord(GameWord word);

    void updateWord(GameWord word);

    void deleteWordById(Long id);

    int deleteWordByIds(Long[] ids);

    List<GameWord> selectRandomWords(int count, String difficulty);

    List<GameRecord> selectRecordList(GameRecord record);

    GameRecord selectRecordById(Long id);

    void insertRecord(GameRecord record);

    List<GameRecord> selectRecordsByFamilyId(Long familyId);
}
