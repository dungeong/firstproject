package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor     // Article() 생성자 대체
@NoArgsConstructor      // 기본 생성자 추가 어노테이션
@ToString               // toString() 메서드 대체
@Getter                 // 게터 추가
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

//    public Long getId() {
//        return id;
//    }

}