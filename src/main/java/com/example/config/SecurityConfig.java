package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 12/03/14
 * Time: 09:01
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public AuthenticationSuccessHandler successHandler() {
        AuthenticationSuccessHandler successHandler = new UrlAuthenticationSuccessHandler();
        return successHandler;
    }



    @Autowired
    public void registerGlobalAuthentication(
            AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("customer").password("password").roles("CUSTOMER").and()
                .withUser("admin").password("password").roles("ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/signin", "/error/**", "/fragments/**", "/layout/**", "/resources/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/customer/**").hasRole("CUSTOMER")
                .and()
                .formLogin()
                    .loginPage("/signin")
                    .failureUrl("/signin?error=1")
                    .successHandler(successHandler())
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("j_username").passwordParameter("j_password")
                .and()
                .logout()
                .permitAll();

       http.exceptionHandling().accessDeniedPage("/signin");



    }
}
