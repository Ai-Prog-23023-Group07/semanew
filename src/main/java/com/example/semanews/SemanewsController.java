package com.example.semanews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequestMapping("/genres")
public class SemanewsController {

    private final SemanewsRepository semanewsRepository;

    @Autowired
    public SemanewsController(SemanewsRepository semanewsRepository) {
        this.semanewsRepository = semanewsRepository;
    }

    @GetMapping("/getGenre/{name}")
    public List<GenreEntity> getGenre() {
        return semanewsRepository.findGenre();
    }

    @RequestMapping("/main")
    private String showGenre(Model model){
        List<GenreEntity> genreEntities = semanewsRepository.findGenre();
        GenreEntity g = genreEntities.get(0);
        System.out.println(g);
        String a = "テストとと";
        model.addAttribute("genreEntites", g);
        model.addAttribute("str", a);


        return "/semNewsTest.html";
    }
}

