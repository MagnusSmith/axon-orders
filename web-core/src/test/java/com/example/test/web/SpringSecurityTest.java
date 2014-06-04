package com.example.test.web;

import com.example.config.SecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 12/05/2014
 * Time: 16:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@EnableWebSecurity
@ContextConfiguration(classes = {SecurityConfig.class, TestWebMvcConfig.class, OrderMvcControllerTestContextConfiguration.class})

public class SpringSecurityTest {
    @Resource
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private WebApplicationContext context;


    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain)


                .build();
    }


    @Test
    public void customer_should_be_signed_in() throws Exception {
        User user = new User("customer", "password", AuthorityUtils.createAuthorityList("ROLE_CUSTOMER"));
        UsernamePasswordAuthenticationToken principal = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(principal);


        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new MockSecurityContext(principal));


        mockMvc.perform(get("/customer/home").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Customer Home")));
    }


    @Test
    public void customer_should_be_redirected_to_sigin() throws Exception {
//        User user = new User("customer","password", AuthorityUtils.createAuthorityList("ROLE_CUSTOMER"));
//        UsernamePasswordAuthenticationToken principal  = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(principal);


//        MockHttpSession session = new MockHttpSession();
//        session.setAttribute(
//                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
//                new MockSecurityContext(principal));


        mockMvc.perform(get("/customer/home"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("http://localhost/customer_signin"))
                .andDo(print());
//                .andExpect(content().string(containsString("Sign in")));

    }

    private static ResultMatcher redirectedUrlPattern(final String expectedUrlPattern) {
        return result -> {
            Pattern pattern = Pattern.compile("\\A" + expectedUrlPattern + "\\z");
            assertTrue(pattern.matcher(result.getResponse().getRedirectedUrl()).find());
        };
    }

    public static class MockSecurityContext implements SecurityContext {

        private static final long serialVersionUID = -1386535243513362694L;

        private Authentication authentication;

        public MockSecurityContext(Authentication authentication) {
            this.authentication = authentication;
        }

        @Override
        public Authentication getAuthentication() {
            return this.authentication;
        }

        @Override
        public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;
        }
    }
}
