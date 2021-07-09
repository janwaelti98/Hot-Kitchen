package ch.fhnw.webeC.model;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("Test")
public class RatingTest {

    @Test
    public void createRatingTest() {
        User mockUser1 = mock(User.class);
        when(mockUser1.getId()).thenReturn(1);
        User mockUser2 = mock(User.class);
        when(mockUser2.getId()).thenReturn(2);
        User mockUser3 = mock(User.class);
        when(mockUser3.getId()).thenReturn(3);

        Rating newRating1 = new Rating(mockUser1, 5);
        Rating newRating2 = new Rating(mockUser2, 4);
        Rating newRating3 = new Rating(mockUser3, 3);

        assertEquals(1, newRating1.getReviewer().getId());
        assertEquals(5, newRating1.getRatingNumber());
        assertEquals(java.sql.Date.valueOf(LocalDate.now()), newRating1.getReviewDate());
        assertEquals(2, newRating2.getReviewer().getId());
        assertEquals(4, newRating2.getRatingNumber());
        assertEquals(java.sql.Date.valueOf(LocalDate.now()), newRating2.getReviewDate());
        assertEquals(3, newRating3.getReviewer().getId());
        assertEquals(3, newRating3.getRatingNumber());
        assertEquals(java.sql.Date.valueOf(LocalDate.now()), newRating3.getReviewDate());
    }
}
