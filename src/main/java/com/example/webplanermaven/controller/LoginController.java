package com.example.webplanermaven.controller;

import com.example.webplanermaven.dto.UserDto;
import com.example.webplanermaven.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@AllArgsConstructor
public class LoginController {

    private final UserService userService;

    private static final String REGISTER = "register";

    private static final String LOGIN = "login";

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("register")
    public String registerNewUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result) {
        log.info("New user = {}", userDto.toString());
        if (result.hasErrors())
            return REGISTER;
        if (!userDto.getPassword().equals(userDto.getRepeatPassword())) {
            result.rejectValue("password", "", "Password not match repeat password");
            return REGISTER;
        }
        if (userService.isUserExist(userDto.getUsername())) {
            result.rejectValue("username", "", "User with this username already exists");
            return REGISTER;
        }
        userService.createUser(userDto);
        return String.format("redirect:/%s", LOGIN);
    }
}
