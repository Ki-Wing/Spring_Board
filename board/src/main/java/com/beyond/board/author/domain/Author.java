package com.beyond.board.author.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.beyond.board.author.dto.AuthorDetailDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.dto.AuthorUpdateDto;
import com.beyond.board.common.BaseTimeEntity;
import com.beyond.board.post.domain.Post;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
// builder 어노테이션을 통해 매개변수의 순서, 매개변수의 개수 등을 유연하게 세팅하여 생성자로서 활용
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 일반적으로 부모엔티티(자식 객체에 영향을 끼칠 수 있는 엔티티 말함)에 cascade 옵션 설정

    // 회원이 쓴 글의 수 조회할때 사용 가능
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();


    public AuthorListResDto fromEntity(){
        AuthorListResDto authorListResDto = AuthorListResDto.builder()
                .email(this.email).name(this.name).id(this.id)
                .build();
        return authorListResDto;
    }

    public void updateAuthor(AuthorUpdateDto dto){
        this.name = dto.getName();
        this.password = dto.getPassword();
    }
    // 이걸 걍 AuthorDetailDto에 넣었음
//    public AuthorDetailDto fromDetEntity(){
//        AuthorDetailDto authorDetailDto = AuthorDetailDto.builder()
//                .email(this.email).name(this.name).id(this.id).createdTime(this.createdTime).updatedTime(this.updatedTime)
//                .build();
//        return authorDetailDto;
//    }

    // 생성자
//    @Builder // 유연하게 매개변수 사용 가능(순서도 상관 x). 근데 위에 AllAr~ NoAr 하면 밑에 작성 x
//    public Author(String name, String email, String password, Role role){
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.role=role;
//    }

}
