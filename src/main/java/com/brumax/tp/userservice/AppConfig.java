package com.brumax.tp.userservice;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UserConfig.class)
public class AppConfig {
}
