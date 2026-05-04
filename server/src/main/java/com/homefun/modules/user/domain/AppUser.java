package com.homefun.modules.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.homefun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("app_user")
public class AppUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String openid;
    private String nickName;
    private String phone;
    private String avatar;
    private String gender;
    private Long familyId;
    private String roleInFamily;
    private String status;
    private Integer totalScore;
    private Integer gameCount;
    private Integer activityCount;
    private String loginIp;
    private java.util.Date loginDate;

    @TableField(exist = false)
    private String familyName;

    @TableField(exist = false)
    private String newPassword;
}
