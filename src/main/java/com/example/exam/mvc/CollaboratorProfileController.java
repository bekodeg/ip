package com.example.exam.mvc;


import com.example.exam.DTOs.RepositoryDTO;
import com.example.exam.core.config.Constants;
import com.example.exam.core.security.UserPrincipal;
import com.example.exam.core.utils.PageAttributesMapper;
import com.example.exam.services.CollaboratorService;
import com.example.exam.services.RepositoryService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CollaboratorProfileController {
    private static final String PROFILE_VIEW = "profile";

    private static final String PAGE_ATTRIBUTE = "page";

    private final RepositoryService repositoryService;

    public CollaboratorProfileController(CollaboratorService collaboratorService, RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }


    @GetMapping
    public String getProfile(
            @RequestParam(name = PAGE_ATTRIBUTE, defaultValue = "0") int page,
            Model model,
            @AuthenticationPrincipal UserPrincipal principal) {
        final long userId = principal.getId();
        model.addAttribute(PAGE_ATTRIBUTE, page);
        model.addAllAttributes(PageAttributesMapper
                .toAttributes(repositoryService.getAll(userId, page, Constants.DEFAULT_PAGE_SIZE),
                RepositoryDTO::new));
        return PROFILE_VIEW;
    }

    @PostMapping("/delete/{id}")
    public String deleteRepository(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = PAGE_ATTRIBUTE, defaultValue = "0") int page,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserPrincipal principal) {
        redirectAttributes.addAttribute(PAGE_ATTRIBUTE, page);
        repositoryService.delete(id);
        return Constants.REDIRECT_VIEW + "/";
    }
}
