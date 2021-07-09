package ch.fhnw.webeC.model;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("Test")
public class RecipeTest {
    @Test
    public void averageRatingOnEmptyRatingListTest() {
        Recipe recipe = new Recipe();
        var result = recipe.getAverageRating();
        assertEquals(-1, result);
    }

    @Test
    public void averageRatingOnOneInputInListTest() {
        Rating mockRating1 = mock(Rating.class);
        when(mockRating1.getRatingNumber()).thenReturn(4);
        when(mockRating1.getTitle()).thenReturn("Rating1");

        List<Rating> ratings = new ArrayList<>();
        ratings.add(mockRating1);

        Recipe recipe = new Recipe();
        recipe.setRatings(ratings);

        var result = recipe.getAverageRating();
        assertEquals(4.0, result);

    }

    @Test
    public void averageRatingTest() {
        Rating mockRating1 = mock(Rating.class);
        when(mockRating1.getRatingNumber()).thenReturn(4);
        Rating mockRating2 = mock(Rating.class);
        when(mockRating2.getRatingNumber()).thenReturn(3);

        List<Rating> ratings = new ArrayList<>();
        ratings.add(mockRating1);
        ratings.add(mockRating2);

        Recipe recipe = new Recipe();
        recipe.setRatings(ratings);

        var result = recipe.getAverageRating();
        assertEquals(3.5, result);
    }

    @Test
    public void specificRatingNumberTest() {
        Rating mockRating1 = mock(Rating.class);
        when(mockRating1.getRatingNumber()).thenReturn(4);
        when(mockRating1.getId()).thenReturn(1);
        Rating mockRating2 = mock(Rating.class);
        when(mockRating2.getRatingNumber()).thenReturn(3);
        when(mockRating2.getId()).thenReturn(2);

        User mockUser1 = mock(User.class);
        when(mockUser1.getId()).thenReturn(1);
        when(mockRating1.getReviewer()).thenReturn(mockUser1);

        User mockUser2 = mock(User.class);
        when(mockUser2.getId()).thenReturn(2);
        when(mockRating2.getReviewer()).thenReturn(mockUser2);

        List<Rating> ratings = new ArrayList<>();
        ratings.add(mockRating1);
        ratings.add(mockRating2);

        Recipe recipe = new Recipe();
        recipe.setRatings(ratings);

        assertEquals(4, recipe.getSpecificRatingNumber(1));
        assertEquals(3, recipe.getSpecificRatingNumber(2));
    }

    @Test
    public void specificRatingNumberIDNonexistentTest() {
        Rating mockRating1 = mock(Rating.class);
        when(mockRating1.getRatingNumber()).thenReturn(4);
        when(mockRating1.getId()).thenReturn(1);
        Rating mockRating2 = mock(Rating.class);
        when(mockRating2.getRatingNumber()).thenReturn(3);
        when(mockRating2.getId()).thenReturn(2);

        User mockUser1 = mock(User.class);
        when(mockUser1.getId()).thenReturn(1);
        when(mockRating1.getReviewer()).thenReturn(mockUser1);
        User mockUser2 = mock(User.class);
        when(mockUser2.getId()).thenReturn(2);
        when(mockRating2.getReviewer()).thenReturn(mockUser2);

        List<Rating> ratings = new ArrayList<>();
        ratings.add(mockRating1);
        ratings.add(mockRating2);

        Recipe recipe = new Recipe();
        recipe.setRatings(ratings);

        assertEquals(0, recipe.getSpecificRatingNumber(3));
        assertEquals(0, recipe.getSpecificRatingNumber(4));
    }

    @Test
    public void specificRatingIdTest() {
        Rating mockRating1 = mock(Rating.class);
        when(mockRating1.getId()).thenReturn(22);
        Rating mockRating2 = mock(Rating.class);
        when(mockRating2.getId()).thenReturn(12);

        User mockUser1 = mock(User.class);
        when(mockUser1.getId()).thenReturn(1);
        when(mockRating1.getReviewer()).thenReturn(mockUser1);

        User mockUser2 = mock(User.class);
        when(mockUser2.getId()).thenReturn(2);
        when(mockRating2.getReviewer()).thenReturn(mockUser2);

        List<Rating> ratings = new ArrayList<>();
        ratings.add(mockRating1);
        ratings.add(mockRating2);

        Recipe recipe = new Recipe();
        recipe.setRatings(ratings);

        assertEquals(22, recipe.getSpecificRating(1));
        assertEquals(12, recipe.getSpecificRating(2));
    }

    @Test
    public void specificRatingIdNonexistent() {
        Rating mockRating1 = mock(Rating.class);
        when(mockRating1.getId()).thenReturn(1);
        Rating mockRating2 = mock(Rating.class);
        when(mockRating2.getId()).thenReturn(2);

        User mockUser1 = mock(User.class);
        when(mockUser1.getId()).thenReturn(1);
        when(mockRating1.getReviewer()).thenReturn(mockUser1);

        User mockUser2 = mock(User.class);
        when(mockUser2.getId()).thenReturn(2);
        when(mockRating2.getReviewer()).thenReturn(mockUser2);

        List<Rating> ratings = new ArrayList<>();
        ratings.add(mockRating1);
        ratings.add(mockRating2);

        Recipe recipe = new Recipe();
        recipe.setRatings(ratings);

        assertEquals(0, recipe.getSpecificRating(3));
        assertEquals(0, recipe.getSpecificRating(23));
    }

    @Test
    public void addRatingTest() {
        Rating mockRating1 = mock(Rating.class);
        when(mockRating1.getId()).thenReturn(1);
        Rating mockRating2 = mock(Rating.class);
        when(mockRating2.getId()).thenReturn(2);

        User mockUser1 = mock(User.class);
        when(mockUser1.getId()).thenReturn(1);
        when(mockRating1.getReviewer()).thenReturn(mockUser1);

        User mockUser2 = mock(User.class);
        when(mockUser2.getId()).thenReturn(2);
        when(mockRating2.getReviewer()).thenReturn(mockUser2);

        List<Rating> ratings = new ArrayList<>();

        Recipe recipe = new Recipe();
        recipe.setRatings(ratings);

        assertTrue(recipe.addRating(mockRating1));
        assertTrue(recipe.addRating(mockRating2));
        assertEquals(2, recipe.getRatings().size());
    }

    @Test
    public void addCommentTest() {
        Comment mockComment1 = mock(Comment.class);
        when(mockComment1.getId()).thenReturn(1);
        Comment mockComment2 = mock(Comment.class);
        when(mockComment2.getId()).thenReturn(2);

        User mockUser1 = mock(User.class);
        when(mockUser1.getId()).thenReturn(1);
        when(mockComment1.getCommenter()).thenReturn(mockUser1);

        User mockUser2 = mock(User.class);
        when(mockUser2.getId()).thenReturn(2);
        when(mockComment2.getCommenter()).thenReturn(mockUser2);

        List<Comment> comments = new ArrayList<>();

        Recipe recipe = new Recipe();
        recipe.setComments(comments);

        assertTrue(recipe.addComment(mockComment1));
        assertTrue(recipe.addComment(mockComment2));
        assertEquals(2, recipe.getComments().size());
    }
}
