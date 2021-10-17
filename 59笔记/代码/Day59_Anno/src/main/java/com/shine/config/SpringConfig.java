package com.shine.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScans({
        @ComponentScan("com.shine.advice"),
        @ComponentScan("com.shine.service")
})
@EnableAspectJAutoProxy
public class SpringConfig {
}
