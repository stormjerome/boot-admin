package com.ywy.bootadmin.security;

import com.ywy.bootadmin.security.MyAccessDeniedHandler;
import com.ywy.bootadmin.security.MyAuthenticationFailureHandler;
import com.ywy.bootadmin.security.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SpringSecurity配置
 *
 * @author ywy
 * @date 2020-04-08 17:33
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private MyLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭CSRF
        http.csrf().disable();

        // 解决X-Frame-Options DENY问题
        http.headers().frameOptions().sameOrigin();

        String[] excludeUrls = {"/login.html", "/css/**", "/js/**", "/treetable-lay/**", "/xadmin/**", "/ztree/**"};
        // 认证规则
        http.authorizeRequests()
                // 允许匿名访问
                .antMatchers(excludeUrls).permitAll()
                .anyRequest()
                .authenticated();

        // 登录设置
        http.formLogin()
                // 登录页面
                .loginPage("/login.html")
                // 登录请求地址
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                // 登出
                .and().logout()
                .permitAll()
                // session失效
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler);

        // 未授权异常处理
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
