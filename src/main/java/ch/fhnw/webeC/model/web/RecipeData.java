package ch.fhnw.webeC.model.web;

import ch.fhnw.webeC.model.Ingredient;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipeData implements Serializable {
    public RecipeData() {
        super();
    }

    @NotEmpty(message = "Title can not be empty!")
    private String title;

    @Min(value = 5, message = "Value must be at least 5!")
    @Max(value = 1000, message = "Value can not be greater than 1000!")
    private int entireTime;

    @Min(value = 1, message = "Value must be at least 1!")
    @Max(value = 1000, message = "Value can not be greater than 1000!")
    private int activeTime;

    @Min(value = 1, message = "Value must be at least 1!")
    @Max(value = 8, message = "Value can not be greater than 8!")
    private int servings;

    @NotEmpty(message = "Diet can not be empty!")
    private String diet;

    private List<Ingredient> ingredients = new ArrayList<>();

    private List<String> preparation = new ArrayList<>();

    private List<String> equipment = new ArrayList<>();

    public int id;

    public String image;

    @NotEmpty(message = "Short description can not be empty!")
    public String shortDescription;

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEntireTime() {
        return entireTime;
    }

    public void setEntireTime(int entireTime) {
        this.entireTime = entireTime;
    }

    public int getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(int activeTime) {
        this.activeTime = activeTime;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getPreparation() {
        return preparation;
    }

    public void setPreparation(List<String> preparation) {
        this.preparation = preparation;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }
}
