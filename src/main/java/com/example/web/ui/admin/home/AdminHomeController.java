package com.example.web.ui.admin.home;

import com.example.component.Loggable;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 06/03/14
 * Time: 15:39
 * Copyright Advanced Computer Software Group 2014
 */
@Controller
//@Layout(value = "admin/layouts/default")
@RequestMapping("/admin/home")
public class AdminHomeController {
    @Loggable
    private Logger log;


    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "admin/home/home";
    }
}
