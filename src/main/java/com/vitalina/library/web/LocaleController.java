package com.vitalina.library.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class LocaleController {

    CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();

    @RequestMapping(value = "/")
    public void resolveLocale(HttpServletRequest request,HttpServletResponse response) {
        Locale locale = cookieLocaleResolver.resolveLocale(request);
        cookieLocaleResolver.setLocale(request, response, locale);
    }
}
