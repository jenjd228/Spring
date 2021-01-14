package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.UserService;
import org.example.web.dto.LoginForm;
import org.example.web.dto.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    private final Logger logger = Logger.getLogger(RegisterController.class);
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String register(Model model) {
        logger.info("GET /register returns register_page.html");
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration_page";
    }

    @PostMapping("/auth")
    public String authenticateByName(RegistrationForm registrationForm) {
        if (userService.findUser(registrationForm)) {
            logger.info("user with this name already consists");
            return "redirect:/register";
        } else {
            logger.info("registration is success");
            return "redirect:/books/shelf";
        }
    }
}
