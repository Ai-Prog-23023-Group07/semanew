package com.example.semanews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

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
        List<GenreEntity> genreEntities = genreRepository.findGenre();
        
        List<String> genres = new ArrayList<String>();
        for(int i=0; i<genreEntities.size();i++){
            genres.add(genreEntities.get(i).getName());
        }

        model.addAttribute("genres", genres);

        return "/screen1th.html";
    }
}

