package ch.fhnw.webeC.controller;

import ch.fhnw.webeC.model.Rating;
import ch.fhnw.webeC.model.web.exception.RatingNotFoundException;
import ch.fhnw.webeC.model.web.exception.RecipeNotFoundException;
import ch.fhnw.webeC.repository.UserRepository;
import ch.fhnw.webeC.service.RatingService;
import ch.fhnw.webeC.service.article.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("/recipe")
public class RatingController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingService ratingService;

    @PostMapping("/{id}/addRating")
    public String addRating(@PathVariable int id, @RequestParam("star") int rating ) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            var username = ((UserDetails) principal).getUsername();
            var reviewer = userRepository.findByUserName(username).orElseThrow();
            var recipe = recipeService.findRecipe(id).orElseThrow(RecipeNotFoundException::new);

            if (recipe.getSpecificRatingNumber(reviewer.getId()) == 0) {
                var newRating = ratingService.add(new Rating(reviewer, rating));

                recipe.addRating(newRating);
            } else {
                int ratingId = recipe.getSpecificRating(reviewer.getId());
                var newRating = ratingService.findRating(ratingId).orElseThrow(RatingNotFoundException::new);
                newRating.setRatingNumber(rating);

                ratingService.update(newRating);
            }

            recipeService.update(recipe);
        }

        return "redirect:/recipe/" + id;
    }

    @ExceptionHandler(RecipeNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public String notFound(Model model) {
        model.addAttribute("errorMessage", "Recipe not found");

        return "recipe/Detail";
    }

    @ExceptionHandler(RatingNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public String ratingNotFound(Model model) {
        model.addAttribute("errorMessage", "Rating not found");

        return "recipe/Detail";
    }
}
