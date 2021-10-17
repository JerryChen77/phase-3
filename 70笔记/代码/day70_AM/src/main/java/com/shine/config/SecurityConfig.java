package com.shine.config;

import com.shine.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication().withUser("zhangsan").password(passwordEncoder.encode("123123")).roles();
        //
        auth.userDetailsService(myUserDetailsService);
    }

    /**
     * 详细请求设置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()        // 表单登录
                .loginPage("/login.html")           // 登录页面
                .loginProcessingUrl("/user/login")  // 验证登录的Handler
                .defaultSuccessUrl("/index.html")   // 登录成功的默认页面
                .permitAll()                        // 放行
            .and()
                // 设置不拦截的url
                .authorizeRequests()
                .antMatchers("/user/getUser01","/user/getUser02")   // 不验证的url
                .permitAll()            // 放行
            .and()
                .authorizeRequests()
                .antMatchers("/user/getUser03")     // 匹配url
                .hasRole("user")

                .antMatchers("/user/getUser04")     // 匹配url
                .hasRole("admin")                                // 访问次url需要的角色

            .and()
                // 设置拦截的url
                .authorizeRequests()
                .anyRequest()
                .authenticated()       // 验证

            .and()
                .csrf().disable();    // 禁用跨域伪造请求
    }
}
