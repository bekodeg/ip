package com.example.exam.mvc;

import java.util.Objects;

import com.example.exam.DTOs.CollaboratorSignupDTO;
import com.example.exam.core.config.Constants;
import com.example.exam.models.Collaborator;
import com.example.exam.services.CollaboratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping(CollaboratorSignupController.URL)
public class CollaboratorSignupController {
    public static final String URL = "/signup";

    private static final String SIGNUP_VIEW = "signup";
    private static final String USER_ATTRIBUTE = "collaborator";

    private final CollaboratorService collaboratorService;

    public CollaboratorSignupController(
            CollaboratorService collaboratorService) {
        this.collaboratorService = collaboratorService;
    }

    private Collaborator toEntity(CollaboratorSignupDTO dto) {
        return new Collaborator(dto.getLogin(), dto.getPassword());
    }

    @GetMapping
    public String getSignup(Model model) {
        model.addAttribute(USER_ATTRIBUTE, new CollaboratorSignupDTO());
        return SIGNUP_VIEW;
    }
    @PostMapping
    public String signup(
            @ModelAttribute(name = USER_ATTRIBUTE) @Valid CollaboratorSignupDTO collaborator,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return SIGNUP_VIEW;
        }
        if (!Objects.equals(collaborator.getPassword(), collaborator.getPasswordConfirm())) {
            bindingResult.rejectValue("password", "signup:passwords", "Пароли не совпадают.");
            model.addAttribute(USER_ATTRIBUTE, collaborator);
            return SIGNUP_VIEW;
        }
        collaboratorService.add(toEntity(collaborator));
        return Constants.REDIRECT_VIEW + Constants.LOGIN_URL + "?signup";
    }
}
