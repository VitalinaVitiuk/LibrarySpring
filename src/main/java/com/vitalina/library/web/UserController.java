package com.vitalina.library.web;

import com.vitalina.library.domain.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import com.vitalina.library.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        User user = new User();
        logger.info("register new user: " + user);
        model.addAttribute("newuser", user);
        return "add_new_user";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewUser( @ModelAttribute("newuser")@Valid User user, BindingResult result,Model model) {

        if (result.hasErrors()) {
            return "add_new_user";
        }

        if(userService.getUserByUserName(user.getUsername()) != null) {
            model.addAttribute("errorMessageCode", "error.username.inuse");
            return "error/error";
        }

        logger.info("Adding new user: " + user);
        userService.save(user);
        return "admin";
    }

}
