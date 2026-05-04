package com.homefun.modules.game.controller;

import com.homefun.common.core.domain.AjaxResult;
import com.homefun.common.core.domain.TableDataInfo;
import com.homefun.modules.game.domain.GameWord;
import com.homefun.modules.game.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game/word")
public class GameWordController {

    @Autowired
    private IGameService gameService;

    @GetMapping("/list")
    public TableDataInfo list(GameWord word) {
        List<GameWord> list = gameService.selectWordList(word);
        TableDataInfo dataTable = new TableDataInfo();
        dataTable.setRows(list);
        dataTable.setTotal(list.size());
        dataTable.setCode(200);
        dataTable.setMsg("查询成功");
        return dataTable;
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(gameService.selectWordById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody GameWord word) {
        gameService.insertWord(word);
        return AjaxResult.success("新增词汇成功");
    }

    @PutMapping
    public AjaxResult edit(@RequestBody GameWord word) {
        gameService.updateWord(word);
        return AjaxResult.success("修改词汇成功");
    }

    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        gameService.deleteWordByIds(ids);
        return AjaxResult.success("删除词汇成功");
    }

    @GetMapping("/random/{count}")
    public AjaxResult getRandomWords(@PathVariable int count,
                                      @RequestParam(required = false) String difficulty) {
        List<GameWord> list = gameService.selectRandomWords(count, difficulty);
        return AjaxResult.success(list);
    }
}
