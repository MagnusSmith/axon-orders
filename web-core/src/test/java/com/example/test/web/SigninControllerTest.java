package com.example.test.web;

import com.example.config.SecurityConfig;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.TestingAuthenticationToken;
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
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriver;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
public class SigninControllerTest {
    @Resource
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private WebApplicationContext context;

   // private WebClient webClient;
    private WebDriver driver;

    @Before
    public void setup() {



        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context)
           //     .addFilter(springSecurityFilterChain)
                .build();

      //  webClient = new WebClient();
      //  webClient.setWebConnection(new MockMvcWebConnection(mockMvc));
        driver = new MockMvcHtmlUnitDriver(mockMvc, true);

    }


    @After
    public void destroy() {
        if(driver != null) {
            driver.close();
        }
    }

//    @After
//    public void cleanup() {
//             this.webClient.closeAllWindows();
//    }




    @Test
    public void customer_should_sign_in() throws Exception {
        User user = new User("customer","password", AuthorityUtils.createAuthorityList("ROLE_CUSTOMER"));
        UsernamePasswordAuthenticationToken principal  = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(principal);

             driver.get("http://localhost:8080/axon-orders/customer_signin");

             System.out.println("OUT --> " + driver.getPageSource()) ;
        System.out.println("status  --> " + getResponseCode(driver)) ;
        assertTrue(true);
    }


    public int getResponseCode(WebDriver driver) {
        return Integer.parseInt(driver.findElement(By.id("web_response"))
                .getAttribute("content"));
    }

}
