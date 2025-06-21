package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
//    @Autowired      // 게시글 리파지터리 주입
//    private ArticleRepository articleRepository;
//    // GET
//    @GetMapping("/api/articles")        // URL 요청 접수
//    public List<Article> index() {      // index() 메서드 정의
//        return articleRepository.findAll();
//    }
//    @GetMapping("/api/articles/{id}")
//    public Article show(@PathVariable Long id) {        // URL의 id를 매개변수로 받아오기
//        return articleRepository.findById(id).orElse(null);
//    }
//    // POST
//    @PostMapping("/api/articles")       // URL 요청 접수
//    public Article create(@RequestBody ArticleForm dto) {       // create() 메서드 정의
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }
//    // PATCH
//    @PatchMapping("/api/articles/{id}")     // URL 요청 접수
//    public ResponseEntity<Article> update(@PathVariable Long id,    // update() 메서드 정의
//                                         @RequestBody ArticleForm dto) {
//        // 1. DTO -> 엔티티 변환
//        Article article = dto.toEntity();
//        log.info("id : {}, article : {}", id, article.toString());      // 로그 찍기
//        // 2. 타깃 조회
//        Article target = articleRepository.findById(id).orElse(null);
//        // 3. 잘못된 요청 처리
//        if (target == null || id != article.getId()) {
//            // 400, 잘못된 요청 응답!
//            log.info("잘못된 요청! id: {}, article : {}", id, article.toString());       // 로그 찍기
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);        // ResponseEntity 반환
//        }
//        // 4. 업데이트 및 정상 응답(200)
//        Article updated = articleRepository.save(article);      // article 엔티티 DB에 저장
//        target.patch(article);
//        Article updated = articleRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);      // 정상 응답
//    }
//    // DELETE
//    @DeleteMapping("/api/articles/{id}")        // URL 요청 접수
//    public ResponseEntity<Article> delete(@PathVariable Long id){       // 메서드 정의
//        // 1. 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//        // 2. 잘못된 요청 처리하기
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // 3. 대상 삭제하기
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

    @Autowired
    private ArticleService articleService;      // 서비스 객체 주입

    // GET
    // 전체 게시글 조회
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }
    // 단일 게시글 조회
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);       // 서비스로 게시글 생성
        return (created != null) ?      // 생성하면 정상, 실패하면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);       // 서비스를 통해 게시글 수정
        return (updated != null) ?              // 수정되면 정상, 안 되면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);        // 서비스를 통해 게시글 삭제
        return (deleted != null) ?          // 삭제 결과에 따라 응답 처리
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/transaction-test")       // 여러 게시글 생성 요청 접수
    public ResponseEntity<List<Article>> trannsactionTest       // transactionTest() 메서드 정의
            (@RequestBody List<ArticleForm> dtos) {
        List<Article> createdList = articleService.createArticles(dtos);        // 서비스 호출
        return (createdList != null) ?
            ResponseEntity.status(HttpStatus.OK).body(createdList) :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
