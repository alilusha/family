package com.homefun.modules.activity.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.homefun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("activity")
public class Activity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private Long familyId;
    private String familyName;
    private java.util.Date activityTime;
    private String location;
    private String description;
    private String coverUrl;
    private String participantIds;
    private Integer maxParticipants;
    private Integer participantCount;
    private String status;

    @TableField(exist = false)
    private String statusText;

    public String getStatusText() {
        if (status == null) return "";
        switch (status) {
            case "0": return "待开始";
            case "1": return "进行中";
            case "2": return "已结束";
            case "3": return "已取消";
            default: return "";
        }
    }
}
