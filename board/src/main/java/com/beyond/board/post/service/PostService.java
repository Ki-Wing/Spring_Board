package com.beyond.board.post.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.service.AuthorService;
import com.beyond.board.post.domain.Post;
import com.beyond.board.post.dto.PostDetailResDto;
import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.dto.PostSaveReqDto;
import com.beyond.board.post.dto.PostUpdateDto;
import com.beyond.board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final AuthorService authorService;

    // service 또는 repository를 autowired할지는 상황에 따라 다르나
    // service레벨에서 코드가 고도화돼있고 코드의 중복이 심할 경우 serivce레이어를 autowired
    // 그러나, 순환참조가 발생할 경우에는 repository를 autowired
    
    @Autowired
    public PostService(PostRepository postRepository, AuthorService authorService){
        this.postRepository = postRepository;
        this.authorService = authorService;
    }

    // authorService에서 author객체를 찾아 post의 toEntity에 넘기고,
    // jpa가 author 객체에서 author_id를 찾아 db에는 author_id가 들어감
    public Post postCreate(PostSaveReqDto dto){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Author author = authorService.authorFindByEmail(email);
        String appointment = null;
        LocalDateTime appointmentTime = null;
        if(dto.getAppointment().equals("Y") && !dto.getAppointmentTime().isEmpty() ){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            appointmentTime = LocalDateTime.parse(dto.getAppointmentTime(), dateTimeFormatter);
            LocalDateTime now = LocalDateTime.now();
            if(appointmentTime.isBefore(now)){
                throw new IllegalArgumentException("Wrong DateTime");
            }
        }
        System.out.println(dto);


        // 알아서 author_id가 들어감(객체지향)
        Post post = postRepository.save(dto.toEntity(author, appointmentTime));
        return post;
    }

    public Page<PostListResDto> postList(Pageable pageable){
//        List<Post> posts = postRepository.findAllFetch();
//        List<PostListResDto> postListResDtos = new ArrayList<>();
//        for(Post p : posts){
//            postListResDtos.add(p.listFromEntity());
//        }
//        Page<Post> posts =  postRepository.findAll(pageable);
        Page<Post> posts = postRepository.findByAppointment(pageable, "N");
        Page<PostListResDto> postListResDtos = posts.map(a->a.listFromEntity());
        return postListResDtos;

    }


    //Page가 List를 내부적으로 갖고있음
    public Page<PostListResDto> postListPage(Pageable pageable){
        //findAll()은 return 타입이 List<Post>이기 때문에 자체적으로 커스텀 해야함
        Page<Post> posts =  postRepository.findAll(pageable);
        Page<PostListResDto> postListResDtos = posts.map(a->a.listFromEntity());
        return postListResDtos;
    }



    public PostDetailResDto postDetail(Long id){
        Post post = postRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("not found"));
        return post.detfromEntity();
    }

    @Transactional
    public void delete(Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Post post = postRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
        if(!post.getAuthor().getEmail().equals(email)){
            throw new IllegalArgumentException("본인 게시물이 아닙니다.");
        }
        postRepository.delete(post);
    }

    @Transactional
    public void update(Long id, PostUpdateDto dto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Post post = postRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("post not found"));
        if(!post.getAuthor().getEmail().equals(email)){
            throw new IllegalArgumentException("본인 게시물이 아닙니다.");
        }
        postRepository.save(post);
    }
}
