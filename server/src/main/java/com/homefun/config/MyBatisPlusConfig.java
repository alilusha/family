package com.homefun.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.homefun.modules.*.mapper")
public class MyBatisPlusConfig {
}
