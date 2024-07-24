//package com.beyond.board.post.service;
//
//import org.springframework.batch.core.JobParameter;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
////Batch를 돌리기 위한 스케줄러 정의
//@Component
//public class PostBatchScheduler {
//    // Batch: Chuck
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private PostJobConfiguration postJobConfiguration;
//
//
////    @Scheduled(fixedRate = 1000)
//    @Scheduled(cron = "0 0/1 * * * *")
//    public void batahSchedule(){
////        PostJobConfiguration postJobConfiguration= new PostJobConfiguration();
//        Map<String, JobParameter> configMap = new HashMap<>();
//        configMap.put("time", new JobParameter(System.currentTimeMillis()));
//        JobParameters jobParameters = new JobParameters(configMap);
//        try {
//            jobLauncher.run(postJobConfiguration.excuteJob(), jobParameters);
//        }catch(Exception e){
//            e.printStackTrace();
//
//        }
//    }
//}
