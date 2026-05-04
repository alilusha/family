package com.homefun.modules.game.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("game_record")
public class GameRecord {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String gameType;
    private String gameName;
    private Long familyId;
    private String familyName;
    private String roomId;
    private Integer duration;
    private Integer participantCount;
    private Integer totalScore;
    private String winnerName;
    private String gameData;
    private java.util.Date createTime;

    @TableField(exist = false)
    private String timeStr;

    public String getTimeStr() {
        if (duration != null) {
            int minutes = duration / 60;
            int seconds = duration % 60;
            return minutes + "分" + seconds + "秒";
        }
        return "0分0秒";
    }
}
