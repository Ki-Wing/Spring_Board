package com.beyond.board.post.domain;

import com.beyond.board.author.domain.Author;
import com.beyond.board.common.BaseTimeEntity;
import com.beyond.board.post.dto.PostDetailResDto;
import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.dto.PostUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 3000)
    private String contents;


    private String appointment;
    private LocalDateTime appointmentTime;

    //    post 객체 입장에서는 한 사람이 여러 글을 생성할 수 있음
//    @JoinColumn(nullable = false, name = "autor_email", referencedColumnName = "email")
    // 연관관계의 주인(관리하니까)은 fk가 있는 post임
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;



    public PostListResDto listFromEntity() {
        return PostListResDto.builder()
//                .id(this.id).title(this.title).author(this.Email())

                .id(this.id).title(this.title).author_email(this.author.getEmail())
                .build();
    }

    public PostDetailResDto detfromEntity() {
        PostDetailResDto postDetailResDto = PostDetailResDto.builder()
                .id(this.id).title(this.title).contents(this.contents)
                .author_email(this.author.getEmail())
                        // this.updatedTime 이렇게 선언하면 내 변수를 가져오는데 baseTimeEntity에 변수들은 private이기 때문에 못 가져옴.(당연한 소리임)
                .createdTime(this.getCreatedTime()).updatedTime(this.getUpdatedTime())
                .build();
        return postDetailResDto;
    }

    public void updatePost(PostUpdateDto dto){
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }

    public void updateAppointment(String yn){
        this.appointment = yn;
    }
}
