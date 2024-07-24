//package com.beyond.board.common;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.ServletRequestAttributeEvent;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
////aop code임을 명시
//@Aspect
//@Component
//@Slf4j
//public class AopLogService {
//
//    //aop의 대상(공통화의 대상)이 되는 controller, service등 위치 명시
//    //어노테이션 기준(컨트롤러 어노테이션 붙어있는 애들. 뭐..나중에 Service, Ropositroy등 갈아끼움)
//    @Pointcut("within(@org.springframework.stereotype.Controller *)")  // 모든 controller를 대상으로
//    public void controllerPointCut(){
//
//
//    }
//    //방법 1 around를 통해 controller와 걸쳐져 있는 사용 패턴
//    @Around("controllerPointCut()")
////    jointPoint는 사용자가 실행하려고 하는 코드 의미. 위에서 정의한 PointCut을 의미
//    public Object controllerLogger(ProceedingJoinPoint joinPoint){
//        log.info("aop start");
//        log.info("Method명 :  " + joinPoint.getSignature().getName());
//
////        직접 HttpServicetRequset 객체 꺼내는법
//        ServletRequestAttributes attribute = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attribute.getRequest();
//
//        log.info("HTTP Method" + request.getMethod());
//        Map<String, String[]> parameterMap =  request.getParameterMap();
//        ObjectMapper objectMapper = new ObjectMapper();
//        ObjectNode objectNode = objectMapper.valueToTree(parameterMap);
//        log.info("user inputs : " + objectNode);
//
//
//        Object result = null;
//        try {
////            사용자가 실행하고자하는 코드 실행부
//            result = joinPoint.proceed();
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//        log.info("aop end");
//        return result;
//    }
//
//    // 사용 방법2. Before After 어노테이션 사용
//    @Before("controllerPointCut()")
//    public void beforeController(JoinPoint joinPoint){ //  // joinPoint 란 aop 대상으로 하는 컨트롤러의 특정 메서드를 의미
//        log.info("aop start");
//        log.info("Method명 :  " + joinPoint.getSignature().getName());
//
////        직접 HttpServicetRequset 객체 꺼내는법
//        ServletRequestAttributes attribute = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attribute.getRequest();
//
//        log.info("HTTP Method" + request.getMethod());
//    }
//
//
//    @After("controllerPointCut()")
//    public void afterController(){
//        log.info("end Controller");
//
//    }
//
//}
