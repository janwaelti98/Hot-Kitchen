package ch.fhnw.webeC.service.article;

import ch.fhnw.webeC.model.News;
import ch.fhnw.webeC.model.config.NyTimes;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("Test")
class NewsServiceTest {

    @Autowired
    private NewsService service;

    @Test
    public void nyTimesAvailable()
            throws IOException {
        HttpUriRequest request = new HttpGet(NyTimes.getFullUrl());
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void nyTimesContentType()
            throws IOException {
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet(NyTimes.getFullUrl());
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();

        assertEquals(jsonMimeType, mimeType);
    }

    @Test
    void getNewsTest() {
        service = new NewsService();

        List<News> articles = service.getNews();

        assertTrue(articles.size() > 0);
        assertTrue(articles.size() < 10);
    }
}