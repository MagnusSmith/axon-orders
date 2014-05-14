package com.example;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 01/05/2014
 * Time: 13:37
 * Copyright Advanced Computer Software Group 2014
 */
@Controller
@RequestMapping("/customer/fragment")
public class FragmentController {


    @RequestMapping(method = RequestMethod.GET)
    public String getFragment(Model model) {
        model.addAttribute("greeting", "I was handled in a web fragment.#");

        return "customer/fragment/fragment";
    }

}
