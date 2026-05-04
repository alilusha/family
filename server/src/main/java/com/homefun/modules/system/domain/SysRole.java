package com.homefun.modules.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.homefun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String status;

    @TableName(exist = false)
    private Long[] menuIds;
}
