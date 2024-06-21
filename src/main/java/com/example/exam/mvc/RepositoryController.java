package com.example.exam.mvc;

import com.example.exam.DTOs.RepositoryDTO;
import com.example.exam.core.config.Constants;
import com.example.exam.core.utils.PageAttributesMapper;
import com.example.exam.models.Repository;
import com.example.exam.services.CollaboratorService;
import com.example.exam.services.RepositoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping(RepositoryController.URL)
public class RepositoryController {

    public static final String URL = "/repository";
    private static final String GAME_VIEW = "repository";
    private static final String GAME_EDIT_VIEW = "repository-edit";
    private static final String PAGE_ATTRIBUTE = "page";
    private static final String GAME_ATTRIBUTE = "repository";

    private final RepositoryService repositoryService;
    private final CollaboratorService collaboratorService;

    public RepositoryController(RepositoryService repositoryService, CollaboratorService collaboratorService) {
        this.repositoryService = repositoryService;
        this.collaboratorService = collaboratorService;
    }

    @GetMapping
    public String getAll(
            @RequestParam(name = PAGE_ATTRIBUTE, defaultValue = "0") int page,
            Model model) {
        final Map<String, Object> attributes = PageAttributesMapper.toAttributes(
                repositoryService.getAll( page, Constants.DEFAULT_PAGE_SIZE), RepositoryDTO::new);
        model.addAllAttributes(attributes);
        model.addAttribute(PAGE_ATTRIBUTE, page);
        return GAME_VIEW;
    }

    @GetMapping("/edit/")
    public String create(
            @RequestParam(name = PAGE_ATTRIBUTE, defaultValue = "0") int page,
            Model model) {
        model.addAttribute(GAME_ATTRIBUTE, new RepositoryDTO());
        model.addAttribute(PAGE_ATTRIBUTE, page);
        return GAME_EDIT_VIEW;
    }

    @PostMapping("/edit/")
    public String create(
            @RequestParam(name = PAGE_ATTRIBUTE, defaultValue = "0") int page,
            @ModelAttribute(name = GAME_ATTRIBUTE) @Valid RepositoryDTO repository,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(PAGE_ATTRIBUTE, page);
            return GAME_EDIT_VIEW;
        }
        redirectAttributes.addAttribute(PAGE_ATTRIBUTE, page);
        repositoryService.add(new Repository(
                repository.getName(),
                collaboratorService.find(repository.getCollaborator())));
        return Constants.REDIRECT_VIEW + URL;
    }

    @GetMapping("/edit/{id}")
    public String update(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = PAGE_ATTRIBUTE, defaultValue = "0") int page,
            Model model) {
        if (id <= 0) {
            throw new IllegalArgumentException();
        }
        model.addAttribute(GAME_ATTRIBUTE, new RepositoryDTO(repositoryService.find(id)));
        model.addAttribute(PAGE_ATTRIBUTE, page);
        return GAME_EDIT_VIEW;
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = PAGE_ATTRIBUTE, defaultValue = "0") int page,
            @ModelAttribute(name = GAME_ATTRIBUTE) @Valid RepositoryDTO repository,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(PAGE_ATTRIBUTE, page);
            return GAME_EDIT_VIEW;
        }
        if (id <= 0) {
            throw new IllegalArgumentException();
        }
        redirectAttributes.addAttribute(PAGE_ATTRIBUTE, page);
        repositoryService.update(new Repository(
                id,
                repository.getName(),
                collaboratorService.find(repository.getCollaborator())));
        return Constants.REDIRECT_VIEW + URL;
    }

    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = PAGE_ATTRIBUTE, defaultValue = "0") int page,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(PAGE_ATTRIBUTE, page);
        repositoryService.delete(id);
        return Constants.REDIRECT_VIEW + URL;
    }
}
