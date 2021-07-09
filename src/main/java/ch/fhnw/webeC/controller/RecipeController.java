package ch.fhnw.webeC.controller;

import ch.fhnw.webeC.model.Ingredient;
import ch.fhnw.webeC.model.Recipe;
import ch.fhnw.webeC.model.web.RecipeData;
import ch.fhnw.webeC.model.web.exception.RecipeNotFoundException;
import ch.fhnw.webeC.repository.UserRepository;
import ch.fhnw.webeC.service.article.RecipeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
    private static final String REDIRECT_RECIPE = "redirect:/recipe";

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showRecipes(Model recipeModel) {
        recipeModel.addAttribute("recipes", recipeService.getRecipes());

        return "recipe/Index";
    }

    @GetMapping("/add")
    public String addNewRecipe(final Model model) {
        model.addAttribute("recipeData", new RecipeData());

        return "recipe/Add";
    }

    @PostMapping("/add")
    public String addNewRecipe(final @Valid RecipeData recipeData, final BindingResult bindingResult,
                               final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("recipeForm", recipeData);
            return "recipe/Add";
        }

        recipeService.add(new Recipe(), recipeData);

        return REDIRECT_RECIPE;
    }

    @RequestMapping(value = "/add", params = {"addIngredient"})
    public String addIngredient(final RecipeData recipeData, final BindingResult bindingResult) {
        recipeData.getIngredients().add(new Ingredient());

        return "recipe/Add";
    }

    @RequestMapping(value = "/add", params = {"removeIngredient"})
    public String removeIngredient(
            final RecipeData recipeData, final BindingResult bindingResult,
            final HttpServletRequest req) {
        final int id = Integer.parseInt(req.getParameter("removeIngredient"));
        recipeData.getIngredients().remove(id);

        return "recipe/Add";
    }

    @RequestMapping(value = "/add", params = {"addEquipment"})
    public String addEquipment(final RecipeData recipeData, final BindingResult bindingResult) {
        recipeData.getEquipment().add("");

        return "recipe/Add";
    }

    @RequestMapping(value = "/add", params = {"removeEquipment"})
    public String removeEquipment(
            final RecipeData recipeData, final BindingResult bindingResult,
            final HttpServletRequest req) {
        final int id = Integer.parseInt(req.getParameter("removeEquipment"));
        recipeData.getEquipment().remove(id);

        return "recipe/Add";
    }

    @RequestMapping(value = "/add", params = {"addPreparation"})
    public String addPreparation(final RecipeData recipeData, final BindingResult bindingResult) {
        recipeData.getPreparation().add("");

        return "recipe/Add";
    }

    @RequestMapping(value = "/add", params = {"removePreparation"})
    public String removePreparation(
            final RecipeData recipeData, final BindingResult bindingResult,
            final HttpServletRequest req) {
        final int id = Integer.parseInt(req.getParameter("removePreparation"));
        recipeData.getPreparation().remove(id);

        return "recipe/Add";
    }

    @GetMapping("/{id}")
    public String showRecipeDetail(@PathVariable int id, Model model) {
        var recipe = recipeService.findRecipe(id).orElseThrow(RecipeNotFoundException::new);
        model.addAttribute("recipe", recipe);
        model.addAttribute("comments", recipe.getComments());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            var username = ((UserDetails) principal).getUsername();
            var reviewer = userRepository.findByUserName(username).orElseThrow();
            var reviewerId = reviewer.getId();
            var ratingNumber = recipe.getSpecificRatingNumber(reviewerId);
            model.addAttribute("loggedInUserRating", ratingNumber);
        }

        return "recipe/Detail";
    }

    @GetMapping("{id}/edit")
    public String editRecipeDetail(@PathVariable int id, Model model) {
        var recipe = recipeService.findRecipe(id).orElseThrow(RecipeNotFoundException::new);
        var recipeData = new RecipeData();

        BeanUtils.copyProperties(recipe, recipeData);
        model.addAttribute("recipeData", recipeData);

        return "recipe/Edit";
    }

    @PostMapping("{id}/edit")
    public String editRecipeDetail(final @Valid RecipeData recipeData, final BindingResult bindingResult,
                                   final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("recipeForm", recipeData);

            return "recipe/Edit";
        }

        var recipe = recipeService.findRecipe(recipeData.getId()).orElseThrow(RecipeNotFoundException::new);
        BeanUtils.copyProperties(recipeData, recipe);

        recipeService.update(recipe);

        return "redirect:/recipe/" + recipe.getId();
    }

    @RequestMapping(value = "{id}/edit", params = {"editEquipment"})
    public String editEquipment(final RecipeData recipeData, final BindingResult bindingResult) {
        recipeData.getEquipment().add("");

        return "recipe/Edit";
    }

    @RequestMapping(value = "{id}/edit", params = {"removeEditEquipment"})
    public String removeEditEquipment(
            final RecipeData recipeData, final BindingResult bindingResult,
            final HttpServletRequest req) {
        final int id = Integer.parseInt(req.getParameter("removeEditEquipment"));
        recipeData.getEquipment().remove(id);

        return "recipe/Edit";
    }

    @RequestMapping(value = "{id}/edit", params = {"editIngredient"})
    public String editIngredient(final RecipeData recipeData, final BindingResult bindingResult) {
        recipeData.getIngredients().add(new Ingredient());

        return "recipe/Edit";
    }

    @RequestMapping(value = "{id}/edit", params = {"removeEditIngredient"})
    public String removeEditIngredient(
            final RecipeData recipeData, final BindingResult bindingResult,
            final HttpServletRequest req) {
        final int id = Integer.parseInt(req.getParameter("removeEditIngredient"));
        recipeData.getIngredients().remove(id);

        return "recipe/Edit";
    }

    @RequestMapping(value = "{id}/edit", params = {"editPreparation"})
    public String editPreparation(final RecipeData recipeData, final BindingResult bindingResult) {
        recipeData.getPreparation().add("");

        return "recipe/Edit";
    }

    @RequestMapping(value = "{id}/edit", params = {"removeEditPreparation"})
    public String removeEditPreparation(
            final RecipeData recipeData, final BindingResult bindingResult,
            final HttpServletRequest req) {
        final int id = Integer.parseInt(req.getParameter("removeEditPreparation"));
        recipeData.getPreparation().remove(id);

        return "recipe/Edit";
    }

    @PostMapping("{id}/delete")
    public String deleteRecipe(@PathVariable int id) {
        var recipe = recipeService.findRecipe(id).orElseThrow(RecipeNotFoundException::new);
        recipeService.delete(recipe);

        return "redirect:/recipe";
    }

    @ExceptionHandler(RecipeNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public String notFound(Model model) {
        model.addAttribute("errorMessage", "Recipe not found");

        return "recipe/Detail";
    }
}
