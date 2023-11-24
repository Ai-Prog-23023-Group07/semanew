package com.example.semanews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;



@Controller
@RequestMapping("/genres")
public class SemanewsController {

    private final GenreRepository genreRepository;
    private final TopicRepository topicRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public SemanewsController(GenreRepository genreRepository, TopicRepository topicRepository, ArticleRepository articleRepository) {
        this.genreRepository = genreRepository;
        this.topicRepository = topicRepository;
        this.articleRepository = articleRepository;
    }
    
    @RequestMapping("/main")
    private String showGenre(Model model){
        List<GenreEntity> genreEntities = genreRepository.findGenre();
        List<TopicEntity> topicEntities = topicRepository.findTopic();
        List<ArticleEntity> articleEntities = articleRepository.findArticle();

        System.out.println(topicEntities);
        System.out.println(articleEntities);

        List<String> genres = new ArrayList<String>();
        for(int i=0; i<genreEntities.size();i++){
            genres.add(genreEntities.get(i).getName());
        }
        List<String> topics = new ArrayList<String>();
        for(int i=0; i<topicEntities.size();i++){
            topics.add(topicEntities.get(i).getName());
        }
        List<String> articles = new ArrayList<String>();
        for(int i=0; i<articleEntities.size();i++){
            articles.add(articleEntities.get(i).getTitle());
        }

        model.addAttribute("genres", genres);
        model.addAttribute("topics", topics);
        model.addAttribute("articles", articles);

        return "/semNewsTest.html";
    }
}

