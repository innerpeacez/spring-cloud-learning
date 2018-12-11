package com.zhw.ms.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author innerpeacez
 * @since 2018/12/11
 */
@RestController
@RequestMapping("/configInfo")
@RefreshScope
public class ConfigInfoController {

    @Autowired
    private ConfigInfoProperties properties;

    @GetMapping
    public String getConfigInfo() {
        return properties.getConfig();
    }
}
