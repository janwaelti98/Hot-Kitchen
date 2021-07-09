package ch.fhnw.webeC.service;

import ch.fhnw.webeC.model.Rating;
import ch.fhnw.webeC.model.User;
import ch.fhnw.webeC.repository.RatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ActiveProfiles("Test")
@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {
    @Mock
    private RatingRepository repo;

    @InjectMocks
    private RatingService service;

    List<Rating> allRatings;
    User mockUser1;

    @Test
    void findRatingTest() {
        User mockUser1 = mock(User.class);
        when(mockUser1.getId()).thenReturn(1);
        User mockUser2 = mock(User.class);
        when(mockUser2.getId()).thenReturn(2);
        User mockUser3 = mock(User.class);
        when(mockUser3.getId()).thenReturn(3);

        Rating rating1 = new Rating(mockUser1, 1);
        Rating rating2 = new Rating(mockUser2, 2);
        Rating rating3 = new Rating(mockUser3, 3);
        allRatings = new ArrayList<>();
        allRatings.add(rating1);
        allRatings.add(rating2);
        allRatings.add(rating3);

        given(repo.findById(1)).willReturn(java.util.Optional.of(rating1));
        given(repo.findById(2)).willReturn(java.util.Optional.of(rating2));
        given(repo.findById(3)).willReturn(java.util.Optional.of(rating3));

        var res1 = service.findRating(1).orElseThrow();
        assertEquals(1, res1.getRatingNumber());
        assertEquals(1, res1.getReviewer().getId());

        var res2 = service.findRating(2).orElseThrow();
        assertEquals(2, res2.getRatingNumber());
        assertEquals(2, res2.getReviewer().getId());

        var res3 = service.findRating(3).orElseThrow();
        assertEquals(3, res3.getRatingNumber());
        assertEquals(3, res3.getReviewer().getId());
    }

    @Test
    void CommentToRepoTest() {
        mockUser1 = mock(User.class);
        final Rating newRating = new Rating(mockUser1, 5);

        given(repo.save(newRating)).willAnswer(invocation -> invocation.getArgument(0));

        service.add(newRating);

        verify(repo).save(any(Rating.class));
    }
}
