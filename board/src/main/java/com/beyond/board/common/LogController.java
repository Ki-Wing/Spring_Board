package com.beyond.board.common;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j   //Slf4j 어노테이션 통한 로거 선언 방법
public class LogController {
    //slf4j 어노테이션 또는 Logger 직접 선언
    //로거 직접 선언 방법 쓸거임
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);
    
    @GetMapping("log/test1")
    public String logTest1(){
        //기존의 로그 방식 System.out.println
        //문제점1. 성능이 좋지 않음
        //문제점2. 로그 분류작업 불가
//        System.out.println("println 로그다.");
//        logger.debug("debug 로그");
//        logger.info("info 로그입니다");
//        logger.error("error 로그입니다");

        log.info("slfj4 로그임");
        return "ok";
    }

}

