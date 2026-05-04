package com.homefun.modules.game.controller;

import com.homefun.common.core.domain.AjaxResult;
import com.homefun.common.core.domain.TableDataInfo;
import com.homefun.modules.game.domain.GameQuestion;
import com.homefun.modules.game.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game/question")
public class GameQuestionController {

    @Autowired
    private IGameService gameService;

    @GetMapping("/list")
    public TableDataInfo list(GameQuestion question) {
        List<GameQuestion> list = gameService.selectQuestionList(question);
        TableDataInfo dataTable = new TableDataInfo();
        dataTable.setRows(list);
        dataTable.setTotal(list.size());
        dataTable.setCode(200);
        dataTable.setMsg("查询成功");
        return dataTable;
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(gameService.selectQuestionById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody GameQuestion question) {
        gameService.insertQuestion(question);
        return AjaxResult.success("新增题目成功");
    }

    @PutMapping
    public AjaxResult edit(@RequestBody GameQuestion question) {
        gameService.updateQuestion(question);
        return AjaxResult.success("修改题目成功");
    }

    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        gameService.deleteQuestionByIds(ids);
        return AjaxResult.success("删除题目成功");
    }

    @GetMapping("/random/{count}")
    public AjaxResult getRandomQuestions(@PathVariable int count,
                                          @RequestParam(required = false) String difficulty) {
        List<GameQuestion> list = gameService.selectRandomQuestions(count, difficulty);
        return AjaxResult.success(list);
    }
}
