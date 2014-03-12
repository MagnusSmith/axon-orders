package com.example.web.ui.signin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 12/03/14
 * Time: 10:09
 */
@Controller
public class SigninController {

    @RequestMapping(value = "signin", method = RequestMethod.GET)
    public String signin() {
        return "signin/signin";
    }


}
