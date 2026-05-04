package com.homefun.modules.game.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.homefun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("game_question")
public class GameQuestion extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String question;
    private String category;
    private String options;
    private String answer;
    private String difficulty;
    private String explanation;
    private Integer playCount;
    private Integer correctCount;
    private String status;

    @TableField(exist = false)
    private Double correctRate;
}
