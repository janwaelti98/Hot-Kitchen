package ch.fhnw.webeC.model;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("Test")
public class CommentTest {
    @Test
    public void createCommentTest() {
        User mockUser1 = mock(User.class);
        when(mockUser1.getId()).thenReturn(1);

        Comment newComment = new Comment(mockUser1, "This is a new comment");

        assertEquals("This is a new comment", newComment.getText());
        assertEquals(java.sql.Date.valueOf(LocalDate.now()), newComment.getCommentDate());
        assertEquals(1, newComment.getCommenter().getId());
    }

    @Test
    public void commentDateToStringTest() {
        User mockUser1 = mock(User.class);
        when(mockUser1.getId()).thenReturn(1);

        User mockUser2 = mock(User.class);
        when(mockUser2.getId()).thenReturn(2);

        Comment newComment = new Comment(mockUser1, "This is a new comment");
        Comment anotherNewComment = new Comment(mockUser2, "Another new comment");

        assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), newComment.commentDateToString(newComment.getCommentDate()));
        assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), newComment.commentDateToString(anotherNewComment.getCommentDate()));
    }
}
