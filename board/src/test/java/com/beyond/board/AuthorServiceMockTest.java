//package com.beyond.board;
//
//import com.beyond.board.author.domain.Author;
//import com.beyond.board.author.domain.Role;
//import com.beyond.board.author.dto.AuthorDetailDto;
//import com.beyond.board.author.dto.AuthorSaveReqDto;
//import com.beyond.board.author.repository.AuthorRepository;
//import com.beyond.board.author.service.AuthorService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.Optional;
//
//@SpringBootTest
//@Transactional
//public class AuthorServiceMockTest {
//    @Autowired
//    private AuthorService authorService;
//
////    @Autowired
////    private AuthorRepository authorRepository;
//
//
//// 목킹 : 가짜 객체 만드는 작업
//    @MockBean
//    private AuthorRepository authorRepository;
//
//
//    //저장 및 detail 조회
//        //service를 테스트할건데, repositroy가 변수 발생할 여지가 있음 => 가짜 Repository 객체 만듦
//        // 일관된 응답 받을 수 있게함
//    @Test
//    public void findAuthorDetailTest(){
//        AuthorSaveReqDto author = new AuthorSaveReqDto("test1", "testt@test.com", "testtest", Role.USER);
//        Author author1 = authorService.authorCreate(author);
////        Author myAuthor = Author.builder().id(1L).name("test").email("test.com").build();
//        AuthorDetailDto authorDetailDto = authorService.authorDetail(author1.getId());
////        Author savedAuthor = authorRepository.findById(author1.getId())
////                .orElseThrow(()-> new EntityNotFoundException());
//
//        Mockito.when(authorRepository.findById(author1.getId())).thenReturn(Optional.of(author1));
//        Author savedAuthor = authorRepository.findById(author1.getId())
//                .orElseThrow(()-> new EntityNotFoundException());
//        Assertions.assertEquals(authorDetailDto.getEmail(), savedAuthor.getEmail());
//    }
//
//    //update 검증
//
//
//    //findAll 검증
//}
