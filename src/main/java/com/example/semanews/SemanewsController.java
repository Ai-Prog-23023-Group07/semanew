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

    private final SemanewsRepository semanewsRepository;

    @Autowired
    public SemanewsController(SemanewsRepository semanewsRepository) {
        this.semanewsRepository = semanewsRepository;
    }

    @RequestMapping("/main")
    private String showGenre(Model model){
        List<GenreEntity> genreEntities = semanewsRepository.findGenre();
        List<TopicEntity> topicEntities = semanewsRepository.findTopicByGenre(genreEntities.get(0).getName());

        System.out.println(topicEntities);

        //testはジャンル名のみを取り出したString型のリスト
        List<String> genres = new ArrayList<String>();
        for(int i=0; i<genreEntities.size();i++){
            genres.add(genreEntities.get(i).getName());
        }
        List<String> topics = new ArrayList<String>();
        for(int i=0; i<topicEntities.size();i++){
            topics.add(topicEntities.get(i).getName());
        }
        model.addAttribute("genres", genres);
        model.addAttribute("topics", topics);

        return "/semNewsTest.html";
    }
}

