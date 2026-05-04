package com.homefun.modules.media.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.homefun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("media_content")
public class MediaContent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String type;
    private Long categoryId;
    private String coverUrl;
    private String mediaUrl;
    private Integer duration;
    private String description;
    private String director;
    private String actors;
    private Integer year;
    private BigDecimal rating;
    private Integer viewCount;
    private Integer favoriteCount;
    private String status;

    @TableField(exist = false)
    private String categoryName;

    @TableField(exist = false)
    private String durationStr;

    public String getDurationStr() {
        if (duration != null) {
            int hours = duration / 3600;
            int minutes = (duration % 3600) / 60;
            int seconds = duration % 60;
            if (hours > 0) {
                return String.format("%d:%02d:%02d", hours, minutes, seconds);
            }
            return String.format("%d:%02d", minutes, seconds);
        }
        return "0:00";
    }
}
