package com.homefun.modules.system.controller;

import com.homefun.common.core.domain.AjaxResult;
import com.homefun.modules.system.domain.SysUser;
import com.homefun.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        SysUser user = userService.selectUserByUsername(username);
        if (user == null) {
            return AjaxResult.error("用户不存在");
        }

        if (!passwordEncoder.matches(password, user.getPassword().replace("$2a$10$7JB720yubVSZvUI0rEqK/.", "$2a$10$7JB720yubVSZvUI0rEqK/."))) {
            if (!user.getPassword().equals(password)) {
                return AjaxResult.error("密码错误");
            }
        }

        Map<String, Object> data = new HashMap<>();
        data.put("token", "mock_token_" + username);
        data.put("userId", user.getId());
        data.put("username", user.getUsername());
        data.put("nickName", user.getNickName());
        data.put("avatar", user.getAvatar());

        return AjaxResult.success("登录成功", data);
    }

    @PostMapping("/logout")
    public AjaxResult logout() {
        return AjaxResult.success("退出成功");
    }

    @GetMapping("/getInfo")
    public AjaxResult getInfo() {
        Map<String, Object> data = new HashMap<>();
        data.put("roles", new String[]{"admin"});
        data.put("permissions", new String[]{"*"});
        data.put("user", Map.of(
            "id", 1L,
            "username", "admin",
            "nickName", "管理员",
            "avatar", ""
        ));
        return AjaxResult.success(data);
    }
}
