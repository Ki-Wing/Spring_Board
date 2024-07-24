package com.beyond.board;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.author.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

// AuthorRepository 검증 코드임
@SpringBootTest
@Transactional //롤백처리를 위해 Transcational 어노테이션 사용
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;


    @Test
    public void authorSaveTest() {
        // 테스트원리 : save -> 제조화 -> 저장할때만든객체와 비교
        // 준비 단계(prepare, given)
        Author author = Author.builder().name("ynlee3")
                .email("ynlee3@test.com").password("123456789")
                .role(Role.ADMIN).build();
        // 실행(excute, when)
        authorRepository.save(author);
        Author saveAuthor = authorRepository
                .findByEmail("ynlee3@test.com").orElse(null);

        // 검증(then)
        Assertions.assertEquals(author.getEmail(), saveAuthor.getEmail());
    }
}

