package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j      // 로깅 기능
@Controller
public class ArticleController {

    @Autowired      // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입(DI)
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {         // 폼 데이터를 DTO로 받기
//        System.out.println(form.toString());                // DTO에 폼 데이터가 잘 담겼는지 확인
        log.info(form.toString());      // 로깅 코드 추가
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
//        System.out.println(article.toString());             // DTO가 엔티티로 잘 변환되는지 확인 출력
        log.info(article.toString());   // 로깅 코드 추가
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);    // article 엔티티를 저장해 saved 객체에 반환
//        System.out.println(saved.toString());               // article이 DB에 잘 저장되는지 확인 출력
        log.info(saved.toString());     // 로깅 코드 추가
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("articles/{id}")        // 데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);         // id를 잘 받았는지 로그 찍기
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }
}
