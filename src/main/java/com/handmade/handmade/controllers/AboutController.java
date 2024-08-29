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
public class AboutController {

    private final MailService mailService;
    private final Environment environment;

    @Autowired
    public AboutController(Environment environment, MailService mailService) {
        this.environment = environment;
        this.mailService = mailService;
    }
    @GetMapping("/about")
    public String about() {
        return "main/about";
    }

    @GetMapping("/thank-you")
    public String thankYou(Model model) {

        return "thank-you";
    }

    @PostMapping("/about/send-question")
    public String sendQuestion(@ModelAttribute QuestionForm questionForm) {
        mailService.sendEmail(
                questionForm.getFirstName() + " " + questionForm.getLastName(),
                "Зворотній адрес: " + questionForm.getEmail() +
                        "\nІм'я та Прізвище:  " + questionForm.getFirstName() + " " + questionForm.getLastName()+
                        "\n\nЗапитання:\n" + questionForm.getQuestion()
        );
        return "redirect:/thank-you";
    }
}
