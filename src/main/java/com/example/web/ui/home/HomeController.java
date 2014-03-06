package com.example.web.ui.home;

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
public class HomeController {
    @Loggable
    private Logger log;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String index() {
        return "home/home";
    }
}
