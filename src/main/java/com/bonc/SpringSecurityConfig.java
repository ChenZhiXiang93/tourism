package com.bonc;

import com.bonc.code.ValidateCodeFilter;
import com.bonc.security.LoginFailureHandler;
import com.bonc.security.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 16:28 2018/8/19
 * @Modified By:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(-1)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setLoginFailureHandler(loginFailureHandler);
        http
                .addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()  //策略,之后可跟很多子方法
                .antMatchers("/html/login.html","/validCode/*").permitAll()    ///放过
                .anyRequest().authenticated()  //任何请求都需要验证
                .and()
                .logout().logoutSuccessUrl("/html/login.html").permitAll()
                .and()
                .formLogin()    //表单登录
                .loginPage("/html/login.html")
                .loginProcessingUrl("/form/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .and()
                .cors();//新加入

        http.csrf().disable();   //跨站防护关闭
    }

    @Override
    //放过静态资源
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }



}
