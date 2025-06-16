package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller     // 컨트롤러 선언
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {      // 메서드 작성
        model.addAttribute("username", "덩엉");
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "덩멍");
        return "goodbye";
    }

}
