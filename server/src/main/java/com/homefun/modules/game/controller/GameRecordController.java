package com.homefun.modules.game.controller;

import com.homefun.common.core.domain.AjaxResult;
import com.homefun.common.core.domain.TableDataInfo;
import com.homefun.modules.game.domain.GameRecord;
import com.homefun.modules.game.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game/record")
public class GameRecordController {

    @Autowired
    private IGameService gameService;

    @GetMapping("/list")
    public TableDataInfo list(GameRecord record) {
        List<GameRecord> list = gameService.selectRecordList(record);
        TableDataInfo dataTable = new TableDataInfo();
        dataTable.setRows(list);
        dataTable.setTotal(list.size());
        dataTable.setCode(200);
        dataTable.setMsg("查询成功");
        return dataTable;
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(gameService.selectRecordById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody GameRecord record) {
        gameService.insertRecord(record);
        return AjaxResult.success("保存记录成功");
    }

    @GetMapping("/family/{familyId}")
    public AjaxResult getRecordsByFamily(@PathVariable Long familyId) {
        List<GameRecord> list = gameService.selectRecordsByFamilyId(familyId);
        return AjaxResult.success(list);
    }
}
