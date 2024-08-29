package com.handmade.handmade.controllers;


import com.handmade.handmade.models.QuestionForm;
import com.handmade.handmade.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {


    @GetMapping("/")
    public String home(Model model) {

        return "index";
    }










}
