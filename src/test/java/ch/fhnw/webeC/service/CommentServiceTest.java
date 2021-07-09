package ch.fhnw.webeC.service;

import ch.fhnw.webeC.model.Comment;
import ch.fhnw.webeC.model.User;
import ch.fhnw.webeC.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ActiveProfiles("Test")
@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private CommentRepository repo;

    @InjectMocks
    private CommentService service;

    List<Comment> allComments;
    User mockUser1;

    @Test
    void CommentToRepoTest() {
        mockUser1 = mock(User.class);
        final Comment comment3 = new Comment(mockUser1, "Comment 5");

        given(repo.save(comment3)).willAnswer(invocation -> invocation.getArgument(0));

        service.add(comment3);

        verify(repo).save(any(Comment.class));
    }

    @Test
    void findAllCommentsTest() {
        mockUser1 = mock(User.class);
        allComments = new ArrayList<>();
        allComments.add(new Comment(mockUser1, "Comment 1"));
        allComments.add(new Comment(mockUser1, "Comment 2"));
        allComments.add(new Comment(mockUser1, "Comment 3"));

        given(repo.findAll()).willReturn(allComments);

        assertEquals(allComments, service.getComments());
    }
}
