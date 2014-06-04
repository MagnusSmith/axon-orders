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
import org.openqa.selenium.WebElement;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExcecutionListener;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriver;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.Filter;

import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 12/05/2014
 * Time: 16:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration

@ContextConfiguration(classes = {SecurityConfig.class, TestWebMvcConfig.class, OrderMvcControllerTestContextConfiguration.class})
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExcecutionListener.class})
public class SigninControllerTest {
    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private WebApplicationContext context;

   // private WebClient webClient;
    private WebDriver driver;

    @Before
    public void setup() {



        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
          //      .defaultRequest(get("/").with(user("customer").roles("CUSTOMER")))
                .build();

      //  webClient = new WebClient();
      //  webClient.setWebConnection(new MockMvcWebConnection(mockMvc));
        driver = new MockMvcHtmlUnitDriver(mockMvc, false);

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
    @WithMockUser(username = "customer", roles = {"CUSTOMER"})
    public void customer_should_be_authorised() throws Exception {
//        User user = new User("customer","password", AuthorityUtils.createAuthorityList("ROLE_CUSTOMER"));
//        UsernamePasswordAuthenticationToken principal  = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(principal);

             driver.get("http://localhost:8080/axon-orders/customer/home");



             assertTrue(driver.getPageSource().contains("Customer Home"));
             System.out.println("OUT --> " + driver.getPageSource()) ;

     //   System.out.println("status  --> " + getResponseCode(driver)) ;

    }


    @Test
    public void anonymous_should_be_redirected_to_sign_in() throws Exception {

        driver.get("http://localhost:8080/axon-orders/customer/home");

        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
            WebElement btn = driver.findElement(By.id("signInBtn"));
            if (btn.isDisplayed()) {
                break;
            }
        }
        assertTrue(driver.getPageSource().contains("Customer Please Sign In"));
        System.out.println("OUT --> " + driver.getPageSource()) ;
        System.out.println("URL --> " + driver.getCurrentUrl()) ;

    }


    @Test
    public void anonymous_should_sign_in_before_home() throws Exception {

        driver.get("http://localhost:8080/axon-orders/customer/home");

//        long end = System.currentTimeMillis() + 5000;
//        while (System.currentTimeMillis() < end) {
//            WebElement btn = driver.findElement(By.id("signInBtn"));
//            if (btn.isDisplayed()) {
//                break;
//            }
//        }
        assertTrue(driver.getPageSource().contains("Customer Please Sign In"));

        WebElement inputUsername = driver.findElement(By.id("inputUsername"));
        inputUsername.sendKeys("customer");
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputPassword.sendKeys("password");

        WebElement btn = driver.findElement(By.id("signInBtn"));
        btn.submit();

//         end = System.currentTimeMillis() + 3000;
//        while (System.currentTimeMillis() < end) {};
        System.out.println("OUT --> " + driver.getPageSource()) ;
        System.out.println("URL --> " + driver.getCurrentUrl()) ;

    }




}
