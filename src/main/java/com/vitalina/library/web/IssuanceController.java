package com.vitalina.library.web;

import com.vitalina.library.domain.Issuance;
import com.vitalina.library.domain.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vitalina.library.service.BookService;
import com.vitalina.library.service.IssuanceService;
import com.vitalina.library.service.UserService;

import java.time.LocalDate;

@Controller
@RequestMapping("/issuances")
@SessionAttributes("userId")
public class IssuanceController {

    @Autowired
    IssuanceService issuanceService;

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;
    Logger logger = Logger.getLogger(IssuanceController.class);

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String showIssuancesToUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.getUserByUserName(name);
        model.addAttribute("issuances", user.getIssuances());
        return "issuances";
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String searchIssuanceByUserId(@RequestParam("userId") Long id, Model model) {
        logger.info("Show issuances by user id: " + id);

        User user = userService.getUserById(id);
        if (user == null){
            model.addAttribute("errorMessageCode", "error.user.notfound");
            return "error/error";
        } else {
            model.addAttribute("issuances", user.getIssuances());
            return "issuances";
        }
    }

    @RequestMapping(value = "searchByDate", method = RequestMethod.GET)
    public String searchByDate(Model model,
                               @RequestParam("fromDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate fromDate,
                               @RequestParam("toDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate toDate){
        logger.info("search from" + fromDate + " to" + toDate);
        if(fromDate.isAfter(toDate)){
            model.addAttribute("errorMessageCode", "error.date.input");
            return "error/error";
        }

        model.addAttribute("issuances", issuanceService.findIsuancesBetweenDates(fromDate, toDate));
        return "issuances";
    }

    @RequestMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

    @RequestMapping(value = "/fulfil", method = RequestMethod.POST)
    public String fulfil(@RequestParam("issuanceId") long id) {
        Issuance issuance = issuanceService.fulfilAndReturn(id);
        return "redirect:/issuances/result?userId=" + issuance.getUser().getId();
    }

    @RequestMapping(value = "/issue", method = RequestMethod.POST)
    public String issue(@RequestParam("issuanceId") Long id) {
        Issuance issuance = issuanceService.issueAndReturn(id);
        return "redirect:/issuances/result?userId=" + issuance.getUser().getId();
    }

    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public String close(@RequestParam("issuanceId") Long id) {
        Issuance issuance = issuanceService.closeAndReturn(id);
        return "redirect:/issuances/result?userId=" + issuance.getUser().getId();
    }
}
