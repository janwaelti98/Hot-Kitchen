package ch.fhnw.webeC.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int id;
    private String text;
    @OneToOne(cascade = {CascadeType.ALL})
    private User commenter;
    private Date commentDate;

    public Comment() {
    }

    public Comment(User commenter, String text) {
        this.commenter = commenter;
        this.text = text;
        this.commentDate = java.sql.Date.valueOf(LocalDate.now());
    }

    public String commentDateToString(Date commentDate) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
        return formatDate.format(commentDate);
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
