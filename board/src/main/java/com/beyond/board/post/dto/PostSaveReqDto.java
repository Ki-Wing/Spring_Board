package com.beyond.board.post.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostSaveReqDto {
    private String title;
    private String contents;
    // 추후 로그인 기능 이후에는 없어질 변수임
//    private String email;
    private String appointment;
    private String appointmentTime;


    public Post toEntity(Author author, LocalDateTime appointmentTime){
        Post post = Post.builder()
// author 객체를 누가 찾냐(PostRepository에서는 못찾음. authorRepository가 찾을거냐 authorService에서 찾을거냐?)
                .title(this.title).contents(this.contents).author(author)
                .appointment(this.appointment).appointmentTime(appointmentTime)
                .build();
        return post;
    }
}
