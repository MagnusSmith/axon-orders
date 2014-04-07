package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 12/03/14
 * Time: 09:01
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    @Autowired
    public void registerGlobalAuthentication(
            AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("customer").password("password").roles("CUSTOMER").and()
                .withUser("admin").password("password").roles("ADMIN");
    }


    @Configuration
    @Order(1)
    public static class CustomerFormLoginWebSecurity extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(WebSecurity web) throws Exception {
            web
                    .ignoring()
                    .antMatchers("/", "/signin/**", "/error/**", "/templates/**", "/resources/**", "/webjars/**");
        }

        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/customer/**").hasRole("CUSTOMER")
                    .and()
                    .formLogin()
                    .loginPage("/customer_signin")
                    .failureUrl("/customer_signin?error=1")
                    .defaultSuccessUrl("/customer/home")
                    .loginProcessingUrl("/j_spring_security_check")
                    .usernameParameter("j_username").passwordParameter("j_password")
                    .and()
                    .logout()
                    .permitAll();

            http.exceptionHandling().accessDeniedPage("/customer_signin");
        }
    }

    @Configuration
    public static class AdminFormLoginWebSecurity extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(WebSecurity web) throws Exception {
            web
                    .ignoring()
                    .antMatchers("/", "/signin/**", "/error/**", "/templates/**", "/resources/**", "/webjars/**");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .and()
                    .formLogin()
                    .loginPage("/admin_signin")
                    .failureUrl("/admin_signin?error=1")
                    .defaultSuccessUrl("/admin/home")
                    .loginProcessingUrl("/j_spring_security_check")
                    .usernameParameter("j_username").passwordParameter("j_password")
                    .and()
                    .logout()
                    .permitAll();

            http.exceptionHandling().accessDeniedPage("/admin_signin");
        }
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/", "/signin", "/error/**", "/templates/**", "/resources/**", "/webjars/**").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                    .antMatchers("/customer/**").hasRole("CUSTOMER")
//                .and()
//                .formLogin()
//                    .loginPage("/signin")
//                    .failureUrl("/signin?error=1")
//                    .successHandler(successHandler())
//                .loginProcessingUrl("/j_spring_security_check")
//                .usernameParameter("j_username").passwordParameter("j_password")
//                .and()
//                .logout()
//                .permitAll();
//
//       http.exceptionHandling().accessDeniedPage("/signin");
//
//
//
//    }
}
