package ch.fhnw.webeC.model;

import javax.persistence.Entity;

@Entity
public class News extends Article {
    private String url;

    public News() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
