package com.example.semanews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
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
    
    @RequestMapping("/screen1")
    private String showscreen1(Model model){
        List<GenreEntity> genres = genreRepository.findGenre();

        model.addAttribute("genres", genres);

        return "/screen1th.html";
    }
    
    @PostMapping("/screen2")
    private String showscreen2(@RequestParam(defaultValue = "") String Genre, @RequestParam(defaultValue = "") String topic, Model model){
        List<TopicEntity> topics = topicRepository.findTopicByGenre(Genre);
        model.addAttribute("genre", Genre);
        model.addAttribute("topics", topics);
        model.addAttribute("topic", topic);

        //topicに値が入っているならscreen3thへ
        if(!topic.equals("")) return "/screen3th.html";

        return "/screen2th.html";
    }

    @PostMapping("/screen3")
    private String showscreen3(@RequestParam(defaultValue = "") String topic, Model model){
        TopicEntity topicEntity = topicRepository.findTopic(topic);
        GenreEntity genre = genreRepository.findGenreByTopic(topic);
        List<TopicEntity> relatedTopics = topicRepository.findRelatedTopic(topic);
        List<ArticleEntity> articles = articleRepository.findArticleByTopic(topic);

        List<String> articleTitles = new ArrayList<>();
        for(int i=0; i<articles.size(); i++){
            articleTitles.add(articles.get(i).getTitle());
        }
        List<String> articleURLs = new ArrayList<>();
        for(int i=0; i<articles.size(); i++){
            articleURLs.add(articles.get(i).getUrl().toString());
        }

        model.addAttribute("thistopic", topicEntity);
        model.addAttribute("genre", genre);
        model.addAttribute("articleTitles", articleTitles);
        model.addAttribute("articleURLs", articleURLs);
        model.addAttribute("relatedTopics", relatedTopics);

        return "/screen3th.html";
    }

    @RequestMapping("/screen4")
    private String showscreen4(Model model){
        List<TopicEntity> topics = topicRepository.findTopic();

        model.addAttribute("topics", topics);

        return "/screen4th.html";
    }

    @RequestMapping("/screen5")
    private String showscreen5(Model model){
        List<ArticleEntity> articles = articleRepository.findArticle();

        List<String> articleTitles = new ArrayList<>();
        for(int i=0; i<articles.size(); i++){
            articleTitles.add(articles.get(i).getTitle());
        }
        List<String> articleURLs = new ArrayList<>();
        for(int i=0; i<articles.size(); i++){
            articleURLs.add(articles.get(i).getUrl().toString());
        }

        model.addAttribute("articleTitles", articleTitles);
        model.addAttribute("articleURLs", articleURLs);

        return "/screen5th.html";
    }
}

