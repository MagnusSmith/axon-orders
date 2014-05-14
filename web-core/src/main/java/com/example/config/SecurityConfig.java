package com.example.config;

import com.example.common.authentication.PathLoginAuthenticationEntryPoint;
import com.example.common.authentication.PathTokens;
import com.example.common.authentication.PathUrlAuthenticationFailureHandler;
import com.example.common.authentication.PathUrlLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 12/03/14
 * Time: 09:01
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = "com.example.common", includeFilters = @ComponentScan.Filter(Component.class), useDefaultFilters = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PathTokens pathTokens() {
        return new PathTokens(Arrays.asList("customer", "admin"));
    }

    @Autowired
    PathLoginAuthenticationEntryPoint loginEntryPoint;

    @Autowired
    PathUrlAuthenticationFailureHandler loginFailureHandler;

    @Autowired
    PathUrlLogoutSuccessHandler logoutSuccessHandler;


    @Autowired
    public void registerGlobalAuthentication(
            AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("customer").password("password").roles("CUSTOMER").and()
                .withUser("admin").password("password").roles("ADMIN");

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/", "/signin/**", "/error/**", "/templates/**", "/resources/**", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/customer/**").hasRole("CUSTOMER")
                .and()
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("j_username").passwordParameter("j_password")
                .failureHandler(loginFailureHandler);

        http.logout().logoutSuccessHandler(logoutSuccessHandler);
        http.exceptionHandling().authenticationEntryPoint(loginEntryPoint);
        http.exceptionHandling().accessDeniedPage("/error/forbidden");

    }
}
