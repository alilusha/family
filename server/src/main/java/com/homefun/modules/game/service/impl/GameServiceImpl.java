package com.homefun.modules.game.service.impl;

import com.homefun.modules.game.domain.GameQuestion;
import com.homefun.modules.game.domain.GameRecord;
import com.homefun.modules.game.domain.GameWord;
import com.homefun.modules.game.mapper.GameQuestionMapper;
import com.homefun.modules.game.mapper.GameRecordMapper;
import com.homefun.modules.game.mapper.GameWordMapper;
import com.homefun.modules.game.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameServiceImpl implements IGameService {

    @Autowired
    private GameQuestionMapper questionMapper;

    @Autowired
    private GameWordMapper wordMapper;

    @Autowired
    private GameRecordMapper recordMapper;

    @Override
    public List<GameQuestion> selectQuestionList(GameQuestion question) {
        return questionMapper.selectQuestionList(question);
    }

    @Override
    public GameQuestion selectQuestionById(Long id) {
        return questionMapper.selectQuestionById(id);
    }

    @Override
    @Transactional
    public void insertQuestion(GameQuestion question) {
        if (question.getStatus() == null) {
            question.setStatus("0");
        }
        if (question.getPlayCount() == null) {
            question.setPlayCount(0);
        }
        if (question.getCorrectCount() == null) {
            question.setCorrectCount(0);
        }
        questionMapper.insertQuestion(question);
    }

    @Override
    @Transactional
    public void updateQuestion(GameQuestion question) {
        questionMapper.updateQuestion(question);
    }

    @Override
    @Transactional
    public void deleteQuestionById(Long id) {
        questionMapper.deleteQuestionById(id);
    }

    @Override
    @Transactional
    public int deleteQuestionByIds(Long[] ids) {
        int count = 0;
        for (Long id : ids) {
            questionMapper.deleteQuestionById(id);
            count++;
        }
        return count;
    }

    @Override
    public List<GameQuestion> selectRandomQuestions(int count, String difficulty) {
        return questionMapper.selectRandomQuestions(count, difficulty);
    }

    @Override
    public List<GameWord> selectWordList(GameWord word) {
        return wordMapper.selectWordList(word);
    }

    @Override
    public GameWord selectWordById(Long id) {
        return wordMapper.selectWordById(id);
    }

    @Override
    @Transactional
    public void insertWord(GameWord word) {
        if (word.getStatus() == null) {
            word.setStatus("0");
        }
        if (word.getPlayCount() == null) {
            word.setPlayCount(0);
        }
        wordMapper.insertWord(word);
    }

    @Override
    @Transactional
    public void updateWord(GameWord word) {
        wordMapper.updateWord(word);
    }

    @Override
    @Transactional
    public void deleteWordById(Long id) {
        wordMapper.deleteWordById(id);
    }

    @Override
    @Transactional
    public int deleteWordByIds(Long[] ids) {
        int count = 0;
        for (Long id : ids) {
            wordMapper.deleteWordById(id);
            count++;
        }
        return count;
    }

    @Override
    public List<GameWord> selectRandomWords(int count, String difficulty) {
        return wordMapper.selectRandomWords(count, difficulty);
    }

    @Override
    public List<GameRecord> selectRecordList(GameRecord record) {
        return recordMapper.selectRecordList(record);
    }

    @Override
    public GameRecord selectRecordById(Long id) {
        return recordMapper.selectRecordById(id);
    }

    @Override
    @Transactional
    public void insertRecord(GameRecord record) {
        recordMapper.insertRecord(record);
    }

    @Override
    public List<GameRecord> selectRecordsByFamilyId(Long familyId) {
        return recordMapper.selectRecordsByFamilyId(familyId);
    }
}
