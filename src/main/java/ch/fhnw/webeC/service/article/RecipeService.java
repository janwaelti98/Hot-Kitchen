package ch.fhnw.webeC.service.article;

import ch.fhnw.webeC.model.Recipe;
import ch.fhnw.webeC.model.web.RecipeData;
import ch.fhnw.webeC.repository.RecipeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepo;

    public RecipeService(RecipeRepository recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    public Optional<Recipe> findRecipe(int id) {
        return recipeRepo.findById(id);
    }

    public List<Recipe> getRecipes() {
        return recipeRepo.findAll();
    }

    public void add(Recipe recipe, RecipeData recipeData) {
        BeanUtils.copyProperties(recipeData, recipe);
        recipeRepo.save(recipe);
    }

    public void update(Recipe recipe) {
        recipeRepo.save(recipe);
    }

    public void delete(Recipe recipe) {
        recipeRepo.delete(recipe);
    }
}
