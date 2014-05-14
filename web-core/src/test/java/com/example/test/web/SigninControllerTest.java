package com.example.test.web;

import com.example.config.SecurityConfig;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 12/05/2014
 * Time: 16:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestWebMvcConfig.class, OrderMvcControllerTestContextConfiguration.class, SecurityConfig.class})
public class SigninControllerTest {


    @Autowired
    private WebApplicationContext context;

    @Autowired
    MockHttpSession session;

    private WebClient webClient;

    @Before
    public void setup() {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        webClient = new WebClient();
        webClient.setWebConnection(new MockMvcWebConnection(mockMvc));
    }


    @After
    public void cleanup() {
             this.webClient.closeAllWindows();
    }


    @Test
    public void initOrderForm_should_return_empty_order_form() throws Exception {
           HtmlPage p = webClient.getPage("http://localhost/axon-orders/customer/home");

        assertTrue(true);
    }
}
