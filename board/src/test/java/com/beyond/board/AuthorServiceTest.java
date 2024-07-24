//package com.beyond.board;
//
//import com.beyond.board.author.domain.Author;
//import com.beyond.board.author.domain.Role;
//import com.beyond.board.author.dto.AuthorSaveReqDto;
//import com.beyond.board.author.dto.AuthorUpdateDto;
//import com.beyond.board.author.repository.AuthorRepository;
//import com.beyond.board.author.service.AuthorService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//public class AuthorServiceTest {
//
//    @Autowired
//    public AuthorService authorService;
//    @Autowired
//    private AuthorRepository authorRepository;
//
////    1. 저장 및 detail 조회
//    @Test
//    void saveAndFind(){
//        AuthorSaveReqDto authorDto = new AuthorSaveReqDto("tete", "tete@test",
//                "1234567890", Role.ADMIN);
//        Author author = authorService.authorCreate(authorDto);
//        Author authorDetail = authorRepository.findById(author.getId())
//                .orElseThrow(()->new EntityNotFoundException());
//        // 검증(then)
//        Assertions.assertEquals(authorDetail.getEmail(), authorDto.getEmail());
//    }
//
//    //2. update 검증
//    @Test
//    void updateTest(){
//        // 객체 create
//        AuthorSaveReqDto authorDto = new AuthorSaveReqDto("tete", "tete@test",
//                "1234567890", Role.ADMIN);
//        Author author = authorService.authorCreate(authorDto);
//
//        // 수정작업(name, password)
//        AuthorUpdateDto updateDto = new AuthorUpdateDto("포항사람이 웃는 소리는?", "포항항항");
//        authorService.update(author.getId(), updateDto);
//
//        // 수정 후 재조회(name, password가 맞는지 각각 검증)
//        Author savedAuthor = authorRepository.findById(author.getId())
//                .orElseThrow(()->new EntityNotFoundException());
//
//        Assertions.assertEquals("포항사람이 웃는 소리는?", savedAuthor.getName());
//        Assertions.assertEquals("포항항항", savedAuthor.getPassword());
//    }
//
//    // 3. findAll 검증
//    @Test
//    public void findAllTest(){
//
//        int size = authorService.authorList().size();
//
//        // 3개 author객체 저장
//        AuthorSaveReqDto authorDto1 = new AuthorSaveReqDto("test1", "test1@test.com", "testtest", Role.USER);
//        AuthorSaveReqDto authorDto2 = new AuthorSaveReqDto("test2", "test2@test.com", "testtest", Role.USER);
//        AuthorSaveReqDto authorDto3 = new AuthorSaveReqDto("test3", "test3@test.com", "testtest", Role.USER);
//
//        authorService.authorCreate(authorDto1);
//        authorService.authorCreate(authorDto2);
//        authorService.authorCreate(authorDto3);
//
//        // authorList 조회한 후 저장한 개수와 저장된 개수 비교
//        List<Author> authors = authorRepository.findAll();
//        Assertions.assertEquals(size+3, authors.size());
//    }
//}
