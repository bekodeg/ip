package com.example.exam.mvc;

import com.example.exam.DTOs.MilestoneDTO;
import com.example.exam.core.config.Constants;
import com.example.exam.core.utils.PageAttributesMapper;
import com.example.exam.models.Milestone;
import com.example.exam.services.MilestoneService;
import com.example.exam.services.RepositoryService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

public class MilestoneController {
    public static final String URL = "/Milestone";
    private static final String GAME_VIEW = "Milestone";
    private static final String GAME_EDIT_VIEW = "Milestone-edit";
    private static final String PAGE_ATTRIBUTE = "page";
    private static final String GAME_ATTRIBUTE = "Milestone";

    private final RepositoryService repositoryService;
    private final MilestoneService milestoneService;

    public MilestoneController(RepositoryService repositoryService, MilestoneService milestoneService) {
        this.repositoryService = repositoryService;
        this.milestoneService = milestoneService;
    }


    @GetMapping
    public String getAll(
            @RequestParam(name = PAGE_ATTRIBUTE, defaultValue = "0") int page,
            Model model) {
        final Map<String, Object> attributes = PageAttributesMapper.toAttributes(
                milestoneService.getAll( page, Constants.DEFAULT_PAGE_SIZE), MilestoneDTO::new);
        model.addAllAttributes(attributes);
        model.addAttribute(PAGE_ATTRIBUTE, page);
        return GAME_VIEW;
    }

    @GetMapping("/edit/")
    public String create(
            @RequestParam(name = PAGE_ATTRIBUTE, defaultValue = "0") int page,
            Model model) {
        model.addAttribute(GAME_ATTRIBUTE, new MilestoneDTO());
        model.addAttribute(PAGE_ATTRIBUTE, page);
        return GAME_EDIT_VIEW;
    }

    @PostMapping("/edit/")
    public String create(
            @RequestParam(name = PAGE_ATTRIBUTE, defaultValue = "0") int page,
            @ModelAttribute(name = GAME_ATTRIBUTE) @Valid MilestoneDTO milestone,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(PAGE_ATTRIBUTE, page);
            return GAME_EDIT_VIEW;
        }
        redirectAttributes.addAttribute(PAGE_ATTRIBUTE, page);
        milestoneService.add(new Milestone(
                milestone.getName(),
                repositoryService.find(milestone.getRepository())));
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
        model.addAttribute(GAME_ATTRIBUTE, new MilestoneDTO(milestoneService.find(id)));
        model.addAttribute(PAGE_ATTRIBUTE, page);
        return GAME_EDIT_VIEW;
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = PAGE_ATTRIBUTE, defaultValue = "0") int page,
            @ModelAttribute(name = GAME_ATTRIBUTE) @Valid MilestoneDTO milestone,
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
        milestoneService.update(new Milestone(
                id,
                milestone.getName(),
                repositoryService.find(milestone.getRepository())));
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
