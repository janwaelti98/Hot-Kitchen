package ch.fhnw.webeC.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Rating {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String text;
    private int ratingNumber;
    @OneToOne(cascade = {CascadeType.ALL})
    private User reviewer;
    private Date reviewDate;

    public Rating() {
    }

    public Rating(User reviewer, int ratingNumber) {
        this.reviewer = reviewer;
        this.ratingNumber = ratingNumber;
        this.reviewDate = java.sql.Date.valueOf(LocalDate.now());
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(int ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
