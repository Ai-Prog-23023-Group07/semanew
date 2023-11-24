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
		succerUrl.add("ニュースのurl3");
		ArrayList<String> baseballUrl = new ArrayList<>();
		baseballUrl.add("野球url1");
		baseballUrl.add("野球url2");
		baseballUrl.add("https://www.chunichi.co.jp/article/804733"); //実際のurlで試す用
		ArrayList<String> keibaUrl = new ArrayList<>();
		keibaUrl.add("けいばurl1");
		keibaUrl.add("けいばurl2");
		keibaUrl.add("けいばurl3");
		
		MiddleGenre succer = new MiddleGenre(bigGenre,middleGenre,succerUrl);
		MiddleGenre baseball = new MiddleGenre(bigGenre,middleGenre2,baseballUrl);
		MiddleGenre keiba = new MiddleGenre(bigGenre,middleGenre3,keibaUrl);
		succer.addRelatedGenre(baseball, keiba);
		baseball.addRelatedGenre(succer, keiba);
		keiba.addRelatedGenre(baseball, succer);
		
		ArrayList<String> midasi=new ArrayList<>();
		midasi.add("hoge1");
		midasi.add("hoge2");
		midasi.add("hoge3");
		succer.setMidasi(midasi);
		baseball.setMidasi(midasi);
		keiba.setMidasi(midasi);
		
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
	ArrayList<String> midasi;
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

	public void setMidasi(ArrayList<String> list) {
		this.midasi=new ArrayList<>(list);
	}
	public ArrayList<String> getMidasi(){
		return this.midasi;
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
	DataBase data = new DataBase();
	@RequestMapping(value="/screen1",params= {"!Genre"}) //screen1表示
	private String showScreen1th(Model model) {
		model.addAttribute("genre1","ジャンル1");
		model.addAttribute("genre2","ジャンル2");
		model.addAttribute("genre3","ジャンル3");
		model.addAttribute("genre4","ジャンル4");
		return "/screen1th.html";
	}
	
	@RequestMapping(value="/screen1",params= {"Genre"}) //Genreボタンが押された際の処理
	private String screen1to2(String Genre,Model model) { //screen1から2に遷移する
		model.addAttribute("genre",Genre);
		model.addAttribute("topic1","トピック1");
		model.addAttribute("topic2","トピック2");
		model.addAttribute("topic3","トピック3");
		return "/screen2th.html";
	}
	
	@RequestMapping(value="/screen2",params= {"topic"}) //topicボタンが押された際の処理
	private String screen2to3(String topic,Model model) { //screen2から3に移る
		if(topic.equals("トピック1")) { //動作確認用
			topic = "サッカー";
		}else if(topic.equals("トピック2")){
			topic="野球";
		}else if(topic.equals("トピック3")) {
			topic="競馬";
		}
		MiddleGenre t=data.getMap().get(topic);
		model.addAttribute("topic",t.getMiddleGenre());
		ArrayList<String> urls = t.getUrl();
		model.addAttribute("url1",urls.get(0));
		model.addAttribute("url2",urls.get(1));
		model.addAttribute("url3",urls.get(2));
		ArrayList<String> midasi=t.getMidasi();
		model.addAttribute("midasi1",midasi.get(0));
		model.addAttribute("midasi2",midasi.get(1));
		model.addAttribute("midasi3",midasi.get(2));
		model.addAttribute("relatedGenre1",t.getRelatedGenre1().getMiddleGenre());
		model.addAttribute("relatedGenre2",t.getRelatedGenre2().getMiddleGenre());
		
		return "/screen3th.html";
	}
	
	@RequestMapping(value="/screen3",params= {"gotop"}) //gotopボタンが押された際の処理
	private String screen3to1(Model model) { //screen3から1に移る
		return showScreen1th(model); //これで動作できた
		
	}
	
	
	
	
	@RequestMapping(value="/screen3",params= {"!RelatedGenre","!gotop"}) //screen3表示
	private String showScreen3th(Model model) {
		
		MiddleGenre succer = data.getMap().get("サッカー");
		model.addAttribute("topic",succer.getMiddleGenre());
		ArrayList<String> urls = succer.getUrl();
		model.addAttribute("url1",urls.get(0));
		model.addAttribute("url2",urls.get(1));
		model.addAttribute("url3",urls.get(2));
		model.addAttribute("midasi1","サッカーニュース○○入団");
		model.addAttribute("midasi2","サッカーニュース○○退団");
		model.addAttribute("midasi3","サッカーニュース○○優勝");
		model.addAttribute("relatedGenre1","野球");
		model.addAttribute("relatedGenre2","競馬");
		
		return "/screen3th.html";
	}
	
	@RequestMapping(value="/screen3",params= {"RelatedGenre"})
	private String showNewScreen3thRelatedGenre1(String RelatedGenre,Model model) { //screen3のジャンルボタン
		DataBase database = new DataBase();
		MiddleGenre relatedgenre1=database.getMap().get(RelatedGenre);
		model.addAttribute("topic",RelatedGenre);
		MiddleGenre middlegenre = data.getMap().get(RelatedGenre);
		ArrayList<String> urls = middlegenre.getUrl();
		model.addAttribute("url1",urls.get(0));
		model.addAttribute("url2",urls.get(1));
		model.addAttribute("url3",urls.get(2));
		model.addAttribute("midasi1","関連するジャンルボタン左");
		model.addAttribute("midasi2","関連するジャンルボタン左");
		model.addAttribute("midasi3","関連するジャンルボタン左");
		model.addAttribute("relatedGenre1",relatedgenre1.getRelatedGenre1().getMiddleGenre());
		model.addAttribute("relatedGenre2",relatedgenre1.getRelatedGenre2().getMiddleGenre());
		
		return "/screen3th.html";
		
	}
	
}