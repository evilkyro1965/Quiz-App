package com.kyrosoft.quiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2/23/2016.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password,isActive as enabled from user where username = ?")
                .authoritiesByUsernameQuery("select username,userType as role from user where username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/pages/index.html").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/**").access("isAuthenticated()")
                .antMatchers("/pages/**").access("isAuthenticated()")
                .antMatchers("/rest/**").access("isAuthenticated()")
                .antMatchers("/rest/**/**").access("isAuthenticated()")
                .antMatchers("/rest/**/**/**").access("isAuthenticated()")
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/pages/index.html",true)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf().disable();

    }

}
