package com.beyond.board.post.repository;

import com.beyond.board.post.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // jpql을 적용하여 네이밍 룰을 통한 방식이 아닌 메서드 생성


    // Page<Post> : List<Post> + 해당 요소의 Page정보
    // Pageable : PageNumber(페이지 번호, 별다른 옵션 없으면 default 첫번째(0번 페이지부터)),
    // Size(몇개씩 한페이지에 들어갈지, default 20개임), 정렬 조건
    Page<Post> findAll(Pageable pageable);

    Page<Post> findByAppointment(Pageable pageable, String appointment);
    
    // select p.*, a.* from p left join author a on p.author_id=a.id
    @Query("select p from Post p left join fetch p.author")
    List<Post> findAllFetch();


    // select p.* from post p left join author a on p.author_id = a.id
    // 아래의 join문은 author 객체를 통한 조건문으로 post를 filtering할 때 사용
    @Query("select p from Post p left join p.author a")
    List<Post> findAllLeftJoin();

}
