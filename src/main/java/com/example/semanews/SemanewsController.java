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

    @GetMapping("/getGenre/{name}")
    public List<GenreEntity> getGenre() {
        return semanewsRepository.findGenre();
    }

    @RequestMapping("/main")
    private String showGenre(Model model){
        List<GenreEntity> genreEntities = semanewsRepository.findGenre();
        String str = genreEntities.get(0).getName();

        //sampleはジャンル名のみを取り出したString型のリスト
        List<String> test = new ArrayList<String>();
        for(int i=0; i<genreEntities.size();i++){
            test.add(genreEntities.get(i).getName());
        }

        model.addAttribute("genreEntites", genreEntities);
        model.addAttribute("str", str);
        model.addAttribute("test", test);

        return "/semNewsTest.html";
    }
}

