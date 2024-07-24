package com.beyond.board.author.controller;

import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.dto.AuthorDetailDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorUpdateDto;
import com.beyond.board.author.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j   //lombok안에 들어가있음
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/create")
    public String authorCreate() {

        return "author/author_register";
    }

    @PostMapping("/create")
    public String authorCreate(@ModelAttribute AuthorSaveReqDto dto) {
        authorService.authorCreate(dto);
        return "redirect:/";
    }

    @GetMapping("/login-screen")
    public String authorloginScreen() {
        return "author/login-screen";
    }

    @GetMapping("/list")
    public String authorList(Model model) {
        List<AuthorListResDto> authorListResDtos = authorService.authorList();
        model.addAttribute("authorList", authorListResDtos);
        return "author/author_list";
    }

    @GetMapping("/list/{id}")
    public String authorDetail(@PathVariable Long id, Model model) {
//        log.info("get요청, parameter : " + id);
//        log.info("method name: authorDetail");

//        try {
            model.addAttribute("author", authorService.authorDetail(id));
//       // 요런식으로 에러 로그 남기는 거임
//        }catch (IllegalArgumentException e){
//            log.error(id + e.getMessage());
//        }

        return "author/author_detail";
    }

    @GetMapping("/delete/{id}")
    public String authorDelete(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/author/list";
    }

    @PostMapping("/update/{id}")
    public String authorUpdate(@PathVariable Long id, @ModelAttribute AuthorUpdateDto authorUpdateDto) {
        authorService.update(id, authorUpdateDto);
        return "redirect:/author/list/" + id;
    }
}
