package ch.fhnw.webeC.model;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe extends Article {
    private int activeTime;
    private int entireTime;
    private int servings;
    private String diet; //e.g. vegetarian, vegan, ...
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Ingredient> ingredients  = new ArrayList<>();
    @ElementCollection
    private List<String> preparation  = new ArrayList<>();
    @ElementCollection
    private List<String> equipment  = new ArrayList<>();
    @ElementCollection
    private List<String> tips  = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Rating> ratings = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Comment> comments  = new ArrayList<>();

    public Recipe() {
    }

    public int getSpecificRatingNumber (int reviewerId) {
        int i=0;
        while(i<ratings.size()){
            if(ratings.get(i).getReviewer().getId() == reviewerId) {
                return ratings.get(i).getRatingNumber();
            }
            i++;
        }
        return 0;
    }

    public int getSpecificRating(int reviewerId) {
        int i=0;
        while(i<ratings.size()){
            if(ratings.get(i).getReviewer().getId() == reviewerId) {
                return ratings.get(i).getId();
            }
            i++;
        }
        return 0;
    }

    public double getAverageRating() {
        double sum = 0;
        double count = 0;
        for (Rating r : this.getRatings()) {
            sum += r.getRatingNumber();
            count++;
        }
        if(count == 0) return -1;
        else {
            double result = sum / count;
            BigDecimal bd = new BigDecimal(Double.toString(result));
            bd = bd.setScale(1, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
    }

    public boolean addRating(Rating rating) {
        return ratings.add(rating);
    }

    public boolean addComment(Comment comment) {
        return this.comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(int activeTime) {
        this.activeTime = activeTime;
    }

    public int getEntireTime() {
        return entireTime;
    }

    public void setEntireTime(int entireTime) {
        this.entireTime = entireTime;
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

    public List<String> getTips() {
        return tips;
    }

    public void setTips(List<String> tips) {
        this.tips = tips;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
