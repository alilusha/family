package com.homefun.modules.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.homefun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private String nickName;
    private String email;
    private String phone;
    private String sex;
    private String avatar;
    private String status;
    private String loginIp;
    private java.util.Date loginDate;

    @TableField(exist = false)
    private Long[] roleIds;

    @TableField(exist = false)
    private String roleId;

    @TableField(exist = false)
    private String roleName;
}
