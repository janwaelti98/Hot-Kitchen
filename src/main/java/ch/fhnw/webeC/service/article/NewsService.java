package ch.fhnw.webeC.service.article;

import ch.fhnw.webeC.model.News;
import ch.fhnw.webeC.model.config.NyTimes;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NewsService {
    public List<News> getNews() {
        List<News> articles = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        String getUrl = NyTimes.getFullUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Map> newsList = restTemplate.exchange(getUrl, HttpMethod.GET, entity, Map.class);

            if (newsList.getStatusCode() == HttpStatus.OK) {

                JSONObject jsonObject = new JSONObject(newsList.getBody());
                JSONObject response = jsonObject.getJSONObject("response");
                JSONArray article = response.getJSONArray("docs");

                for (int i = 0; (i < article.length() && i < 9); i++) {
                    News news = new News();
                    news.setTitle(article.getJSONObject(i).getJSONObject("headline").get("main").toString());
                    news.setShortDescription(article.getJSONObject(i).get("abstract").toString());
                    news.setImage("https://www.nytimes.com/" + article.getJSONObject(i).getJSONArray("multimedia").getJSONObject(0).get("url").toString());
                    news.setUrl(article.getJSONObject(i).get("web_url").toString());
                    articles.add(news);
                }
            }
        } catch (Exception ex) {
            // Exception logging
        }

        return articles;
    }
}
