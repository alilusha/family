package com.homefun.modules.family.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.homefun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("family")
public class Family extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String avatar;
    private String code;
    private Long creatorId;
    private Integer memberCount;
    private String status;

    @TableField(exist = false)
    private String creatorName;

    @TableField(exist = false)
    private Integer activityCount;

    @TableField(exist = false)
    private Integer gameCount;
}
