//package com.beyond.board.post.service;
//
//import com.beyond.board.post.domain.Post;
//import com.beyond.board.post.repository.PostRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//
//@Component
//public class PostScheduler {
//    private final PostRepository postRepository;
//
//
//    @Autowired
//    public PostScheduler(PostRepository postRepository){
//        this.postRepository = postRepository;
//    }
//
//    // 아래 스케줄의 cron 또는 각 자리마다 "초 분 시간 일 월 요일"을 의미
//    // 예를 들어)  * * * * * * : 매월 매요일 매일 매분 매초마다 시작
//    // 예를 들어)  0 0 * * * * : 매일 0분 0초마다 시작 = 1시간에 한번
//    // 예를 들어)  0 0 11 * * * : 매일 11시에
//    // 예를 들어)  0 1 * * * * : 매일 매시 1분에
//    // 예를 들어)  0 0/1 * * * * : 매일 매시 1분마다         -> 많이 쓰임
//    @Scheduled(cron = "0 0/1 * * * *")
//    @Transactional
//    public void postSchedule(){
//        System.out.println("===Reservation Scheduler Start===");
//        Page<Post> posts = postRepository.findByAppointment(Pageable.unpaged(),"Y");
//        for (Post p : posts.getContent()){
//            if(p.getAppointmentTime().isBefore(LocalDateTime.now()) ){
//                p.updateAppointment("N");
//            }
//        }
//        System.out.println("=== Scheduler Finished ===");
//    }
//}
