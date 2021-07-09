package ch.fhnw.webeC.controller;

import ch.fhnw.webeC.service.article.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping("/news")
    public String getNews(final Model model) {
        model.addAttribute("news", newsService.getNews());

        return "news/Index";
    }
}
