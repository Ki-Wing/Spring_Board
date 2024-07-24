package com.beyond.board.post.dto;

import com.beyond.board.author.domain.Author;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostListResDto {
    private Long id;
    private String title;
    //author 객체 그 자체를 return하게 되면 author안에 post가 재참조되어, 순환참조 이슈 발생.

    private String author_email;

    private String appointment;
    private LocalDateTime appointmentTime;

}
