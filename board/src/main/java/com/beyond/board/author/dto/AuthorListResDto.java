package com.beyond.board.author.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data   //toString 필요한 경우도 있으니까
public class AuthorListResDto {
    private Long id;
    private String name;
    private String email;
}
