package ch.fhnw.webeC.controller;

import ch.fhnw.webeC.model.web.UserData;
import ch.fhnw.webeC.model.web.exception.UserAlreadyExistException;
import ch.fhnw.webeC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String register(final Model model) {
        model.addAttribute("userData", new UserData());

        return "account/Register";
    }

    @PostMapping
    public String userRegistration(final @Valid UserData userData, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationForm", userData);
            return "account/Register";
        }

        try {
            userService.register(userData);
        } catch (UserAlreadyExistException e) {
            bindingResult.rejectValue("userName", "userData.userName", "An account already exists for this username.");
            model.addAttribute("registrationForm", userData);
            return "account/Register";
        }

        return "redirect:/Login";
    }
}
