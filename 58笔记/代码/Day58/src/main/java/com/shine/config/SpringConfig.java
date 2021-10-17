package com.shine.config;

import org.springframework.context.annotation.*;


/**
 * 功能类似配置文件的类
 */
@Configuration
@ComponentScans({
        @ComponentScan("com.shine.config"),
        @ComponentScan("com.shine.dao"),
        @ComponentScan("com.shine.entity"),
        @ComponentScan("com.shine.factory"),
        @ComponentScan("com.shine.service"),
})
@Import(SourceConfig.class)
public class SpringConfig {

}
