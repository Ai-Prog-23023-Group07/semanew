package com.example.semanews;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


class DataBase{
	HashMap<String,MiddleGenre> map;
	
	public DataBase() {
		HashMap<String,MiddleGenre> map = new HashMap<>();
		String bigGenre="スポーツ";
		String middleGenre="サッカー";
		String middleGenre2 = "野球";
		String middleGenre3 = "競馬";
		ArrayList<String> succerUrl = new ArrayList<>();
		succerUrl.add("ニュースのurl1");
		succerUrl.add("ニュースのurl2");
		ArrayList<String> baseballUrl = new ArrayList<>();
		baseballUrl.add("野球url1");
		baseballUrl.add("野球url2");
		baseballUrl.add("https://www.chunichi.co.jp/article/804733"); //実際のurl
		ArrayList<String> keibaUrl = new ArrayList<>();
		keibaUrl.add("けいばurl1");
		
		MiddleGenre succer = new MiddleGenre(bigGenre,middleGenre,succerUrl);
		MiddleGenre baseball = new MiddleGenre(bigGenre,middleGenre2,baseballUrl);
		MiddleGenre keiba = new MiddleGenre(bigGenre,middleGenre3,keibaUrl);
		succer.addRelatedGenre(baseball, keiba);
		baseball.addRelatedGenre(succer, keiba);
		keiba.addRelatedGenre(baseball, succer);
		
		map.put("サッカー",succer);
		map.put("野球",baseball);
		map.put("競馬",keiba);
		
		this.map = map;
	}
	
	public HashMap<String,MiddleGenre> getMap() {
		return this.map;
	}
}

class MiddleGenre{
	String bigGenre;
	String middleGenre;
	ArrayList<String> url;
	MiddleGenre relatedGenre1;
	MiddleGenre relatedGenre2;
	
	public MiddleGenre() {
		
	}
	
	public MiddleGenre(String bigGenre,String middleGenre,ArrayList<String> url) {
		this.bigGenre = bigGenre;
		this.middleGenre = middleGenre;
		this.url = url;
		
	}
	
	public MiddleGenre(String bigGenre,String middleGenre,MiddleGenre relatedGenre1,MiddleGenre relatedGenre2,ArrayList<String> url) {
		this.bigGenre = bigGenre;
		this.middleGenre = middleGenre;
		this.relatedGenre1 = relatedGenre1;
		this.relatedGenre2 = relatedGenre2;
		this.url = url;
	}
	
	public ArrayList<String> getUrl(){
		return this.url;
	}
	
	public MiddleGenre getRelatedGenre1() {
		return this.relatedGenre1;
	}
	
	public MiddleGenre getRelatedGenre2() {
		return this.relatedGenre2;
	}
	
	public String getMiddleGenre() {
		return this.middleGenre;
	}
	
	public void addRelatedGenre1(MiddleGenre g) {
		this.relatedGenre1=g;
	}
	
	public void addRelatedGenre2(MiddleGenre g) {
		this.relatedGenre2=g;
	}
	
	public void addRelatedGenre(MiddleGenre relatedGenre1,MiddleGenre relatedGenre2) {
		this.relatedGenre1 = relatedGenre1;
		this.relatedGenre2 = relatedGenre2;
	}
	
}


@Controller
public class DemoController {
	@RequestMapping(value="/screen3demo",params={"!RelatedGenre1","!RelatedGenre2"})  //ボタンが押されていないときの処理
	private String showScreen3(Model model) {
		DataBase database = new DataBase();
		
		MiddleGenre succer = database.getMap().get("サッカー");
		model.addAttribute("middleGenre",succer);
		return "/screen3demo.html";
		
		
	}
	@RequestMapping(value = "/screen3demo",params= {"RelatedGenre1"}) //relatedGenre1が押されたとき
	private String showScreen3RelatedGenre1(String RelatedGenre1,Model model) { //ボタンの名前にはボタンのvalueが入っている
		DataBase database = new DataBase();
		MiddleGenre relatedGenre1 = database.getMap().get(RelatedGenre1);
		model.addAttribute("middleGenre",relatedGenre1);
		return "/screen3demo.html";
	}
	
	@RequestMapping(value = "/screen3demo",params= {"RelatedGenre2"}) //relatedGenre1が押されたとき
	private String showScreen3RelatedGenre2(String RelatedGenre2,Model model) { //ボタンの名前にはボタンのvalueが入っている
		DataBase database = new DataBase();
		MiddleGenre relatedGenre2 = database.getMap().get(RelatedGenre2);
		model.addAttribute("middleGenre",relatedGenre2);
		return "/screen3demo.html";
	}

}