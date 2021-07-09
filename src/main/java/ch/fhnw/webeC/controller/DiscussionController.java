package ch.fhnw.webeC.controller;

import ch.fhnw.webeC.model.Comment;
import ch.fhnw.webeC.repository.UserRepository;
import ch.fhnw.webeC.service.CommentService;
import ch.fhnw.webeC.service.article.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("/recipe")
public class DiscussionController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    private static class RecipeNotFound extends RuntimeException {
    }

    @PostMapping("/{id}/addComment")
    public String addCommentToDiscussion(@PathVariable int id,
                                         @RequestParam @NotBlank String commentText) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            var username = ((UserDetails) principal).getUsername();
            var commenter = userRepository.findByUserName(username).orElseThrow();
            var recipe = recipeService.findRecipe(id).orElseThrow(RecipeNotFound::new);
            var comment = commentService.add(new Comment(commenter, commentText));
            recipe.addComment(comment);
            recipeService.update(recipe);
        }

        return "redirect:/recipe/" + id;
    }

    @ExceptionHandler(RecipeNotFound.class)
    @ResponseStatus(NOT_FOUND)
    public String notFound(Model model) {
        model.addAttribute("errorMessage", "Recipe not found");

        return "recipe/Detail";
    }
}
