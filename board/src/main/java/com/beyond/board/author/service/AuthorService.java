package com.beyond.board.author.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.dto.AuthorDetailDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorUpdateDto;
import com.beyond.board.author.repository.AuthorRepository;
import com.beyond.board.post.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
// 조회 작업 시에 readOnly 설정하면 성능 향상
// 다만, 저장 작업시에 Transactional 필요함
@Transactional(readOnly = true)
public class AuthorService {

    private final PasswordEncoder passwordEncoder;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author authorCreate(AuthorSaveReqDto dto) {
        if(authorRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 email입니다.");
        }
        if(dto.getPassword().length()<8){
            throw new IllegalArgumentException("Passwd is too short");
        }
        Author author = dto.toEntity(passwordEncoder.encode(dto.getPassword()));
        // cascade persist test, remove test는 회원 삭제로 대체함
        author.getPosts().add(Post.builder().author(author).title("가입인사").contents("안녕하십니까" + dto.getName()).build());
        Author savedAuthor = authorRepository.save(author);
        return savedAuthor;
    }

    public List<AuthorListResDto> authorList() {
        List<AuthorListResDto> authorListResDtos = new ArrayList<>();
        List<Author> authors = authorRepository.findAll();
        for(Author a : authors){
            AuthorListResDto authorListResDto = a.fromEntity();
            authorListResDtos.add(authorListResDto);
        }
        return authorListResDtos;
    }

    public AuthorDetailDto authorDetail(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
        AuthorDetailDto authorDetailDto = new AuthorDetailDto();
        return authorDetailDto.fromEntity(author);
    }

    public Author authorFindByEmail(String email){
        Author author = authorRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Email not found"));
        return author;
    }

    @Transactional
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, AuthorUpdateDto dto) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Author not found"));
        author.updateAuthor(dto);
        // jpa가 특정 엔티티에 변경을 자동으로 인지하고 변경사항을 디비에 반영하는게 dirty checking
//        transcational 어노테이션 필요(더티체킹시)
        authorRepository.save(author);
    }
}
