package com.example.web.ui.customer.home;

import com.example.common.logging.Loggable;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 06/03/14
 * Time: 15:39

 */
@Controller
@SessionAttributes("userRoles")
@RequestMapping("/customer/home")
public class CustomerHomeController {
    @Loggable
    private Logger log;


    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "customer/home/home";
    }
}
