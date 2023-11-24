package com.example.semanews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

=======

import java.util.ArrayList;
import java.util.List;
>>>>>>> e7f3f1a49056ac8eabc5226d72a4b3aa9a42c75a



@Controller
<<<<<<< HEAD
=======
@RequestMapping("/genres")
>>>>>>> e7f3f1a49056ac8eabc5226d72a4b3aa9a42c75a
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
    
<<<<<<< HEAD
    @RequestMapping("/screen1")
    private String showscreen1(Model model){
        List<GenreEntity> genreEntities = genreRepository.findGenre();
=======
    @RequestMapping("/main")
    private String showGenre(Model model){
        List<GenreEntity> genreEntities = genreRepository.findGenre();
        List<TopicEntity> topicEntities = topicRepository.findTopic();
        List<ArticleEntity> articleEntities = articleRepository.findArticle();

        System.out.println(topicEntities);
        System.out.println(articleEntities);

        List<TopicEntity> topicEntitiesByGenre = topicRepository.findTopicByGenre("政治");
        List<ArticleEntity> articleEntitiesByTopic = articleRepository.findArticleByTopic("選挙");

        System.out.println(topicEntitiesByGenre);
        System.out.println(articleEntitiesByTopic);
>>>>>>> e7f3f1a49056ac8eabc5226d72a4b3aa9a42c75a
        
        List<String> genres = new ArrayList<String>();
        for(int i=0; i<genreEntities.size();i++){
            genres.add(genreEntities.get(i).getName());
        }
<<<<<<< HEAD

        model.addAttribute("genres", genres);

        return "/screen1th.html";
=======
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
>>>>>>> e7f3f1a49056ac8eabc5226d72a4b3aa9a42c75a
    }
}

