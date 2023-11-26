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
    private String showscreen2(@RequestParam(defaultValue = "") String Genre, Model model){
        List<TopicEntity> topics = topicRepository.findTopicByGenre(Genre);
        model.addAttribute("genre", Genre);
        model.addAttribute("topics", topics);

        return "/screen2th.html";
    }

    @RequestMapping(value="/screen2",params= {"topic"}) //screen2でtopicボタンが押された際の処理
	private String screen2to3(String topic,Model model) { //screen2から3に遷移する
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

    @RequestMapping(value="/screen3",params= {"goGenre"}) //goGenreボタンが押された際の処理
	private String screen3to1(Model model) { //screen3から1に移る
		return showscreen1(model); 
	}

    @RequestMapping(value="/screen3",params= {"goTopic"}) //goTopicボタンが押された際の処理
	private String screen3to4(Model model) { //screen3から4に移る
		return showscreen4(model); 
	}

    @RequestMapping(value="/screen3",params= {"goArticle"}) //goArticleボタンが押された際の処理
	private String screen3to5(Model model) { //screen3から5に移る
		return showscreen5(model); 
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

    @RequestMapping(value="/screen5",params= {"goGenre"}) //goGenreボタンが押された際の処理
	private String screen5to1(Model model) { //screen3から1に移る
		return showscreen1(model); 
	}

    @RequestMapping(value="/screen5",params= {"goTopic"}) //goTopicボタンが押された際の処理
	private String screen5to4(Model model) { //screen3から1に移る
		return showscreen4(model); 
	}
}

