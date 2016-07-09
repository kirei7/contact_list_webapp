package com.vlad.pet.contactlist.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SampleController {
    private static final Logger logger = LoggerFactory.getLogger("debug");
    @RequestMapping (value = "/")
    public String helloWorld(Model model) {
        logger.debug("index page");
        model.addAttribute("pageTitle", "home");
        return "index";
    }
    @RequestMapping (value = "/test")
    public String helloTest(Model model) {
        model.addAttribute("pageTitle", "home");
        return "test";
    }

}
