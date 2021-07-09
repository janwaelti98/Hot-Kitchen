package ch.fhnw.webeC.service.article;

import ch.fhnw.webeC.model.Recipe;
import ch.fhnw.webeC.model.web.RecipeData;
import ch.fhnw.webeC.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ActiveProfiles("Test")
@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository repo;

    @InjectMocks
    private RecipeService service;

    @Test
    void getRecipesTest() {
        Recipe recipe1 = new Recipe();
        recipe1.setTitle("1");
        Recipe recipe2 = new Recipe();
        Recipe recipe3 = new Recipe();

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe1);
        recipeList.add(recipe2);
        recipeList.add(recipe3);

        given(repo.findAll()).willReturn(recipeList);

        var recipes = service.getRecipes();
        assertNotNull(recipes);
        assertFalse(recipes.isEmpty());
        assertEquals(3, recipes.size());
    }

    @Test
    void findRecipeTest() {
        Recipe recipe1 = new Recipe();
        recipe1.setTitle("1");
        Recipe recipe2 = new Recipe();
        Recipe recipe3 = new Recipe();

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe1);
        recipeList.add(recipe2);
        recipeList.add(recipe3);

        given(repo.findById(1)).willReturn(java.util.Optional.of(recipe1));

        var recipe = service.findRecipe(1).orElseThrow();
        assertNotNull(recipe);
        assertEquals("1", recipe.getTitle());
    }

    @Test
    void addRecipeTest() {
        Recipe recipe = new Recipe();
        given(repo.save(recipe)).willAnswer(invocation -> invocation.getArgument(0));

        service.add(recipe, new RecipeData());
        verify(repo).save(any(Recipe.class));
    }
}
