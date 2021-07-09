package ch.fhnw.webeC.model;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("Test")
public class IngredientTest {

    @Test
    public void createIngredientTest() {
        Ingredient newIngredient = new Ingredient("Water", 20);
        Ingredient newIngredient2 = new Ingredient("Flour", 200);
        Ingredient newIngredient3 = new Ingredient("Butter", 75);

        assertEquals("Water", newIngredient.getName());
        assertEquals(20, newIngredient.getAmount());
        assertEquals("Flour", newIngredient2.getName());
        assertEquals(200, newIngredient2.getAmount());
        assertEquals("Butter", newIngredient3.getName());
        assertEquals(75, newIngredient3.getAmount());
    }

}
