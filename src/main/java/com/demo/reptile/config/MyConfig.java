package com.demo.reptile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * MyConfig:
 *
 * @author zhangxiaoxiang
 * @date: 2019/06/21
 */

@Component
public class MyConfig {
    @Value("${server.port}")
    private Integer port;


}
