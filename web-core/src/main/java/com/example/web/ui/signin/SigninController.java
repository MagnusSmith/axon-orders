package com.example.web.ui.signin;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 12/03/14
 * Time: 10:09
 */
@Controller
@SessionAttributes("userRoles")
public class SigninController {

    @RequestMapping(value = "customer_signin", method = RequestMethod.GET)
    public String customerSignin(Model model, HttpServletRequest request) {
        Set<String> userRoles = AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        model.addAttribute("userRole", userRoles);

        if(request.getQueryString() != null){
            model.addAttribute("error", "1");
        }

         return "signin/customer_signin";
    }



    @RequestMapping(value = "admin_signin", method = RequestMethod.GET)
    public String adminSignin(Model model, HttpServletRequest request) {
        Set<String> userRoles = AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        model.addAttribute("userRole", userRoles);

        if(request.getQueryString() != null){
            model.addAttribute("error", "1");
        }

        return "signin/admin_signin";

    }


}
