package com.beyond.board.post.service;


import com.beyond.board.post.domain.Post;
import com.beyond.board.post.repository.PostRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

// batch작업 목록 정의
@Configuration
public class PostJobConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private PostRepository postRepository;

    public Job excuteJob(){
        return jobBuilderFactory.get("excuteJob").start(firstStep()).next(secondStep()).build();
    }

    @Bean
    public Step firstStep(){
        return stepBuilderFactory.get("firstStep")
                .tasklet((stepContribution, chunkContext) -> {
                System.out.println("===Reservation Batch Task1 Start===");
                Page<Post> posts = postRepository.findByAppointment(Pageable.unpaged(),"Y");
                for (Post p : posts){
                    if(p.getAppointmentTime().isBefore(LocalDateTime.now()) ){
                        p.updateAppointment("N");
                    }
                }
                System.out.println("=== Batch Task1 Finished ===");
                return RepeatStatus.FINISHED;
                }).build();

            }


    @Bean
    public Step secondStep(){
        return stepBuilderFactory.get("secondStep")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("===Reservation Batch Task2 Start===");
                    System.out.println("포항 사람이 웃으면? 포항항항");
                    System.out.println("=== Batch Task2 Finished ===");
                    return RepeatStatus.FINISHED;
                }).build();

    }

}
