package com.vitalina.library.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value = {"/index", "/"})
    public String index(boolean error, Model model) {
        model.addAttribute("error", error);
        return "index";
    }

}
