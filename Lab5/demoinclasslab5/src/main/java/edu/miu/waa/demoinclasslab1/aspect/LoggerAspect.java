package edu.miu.waa.demoinclasslab1.aspect;

import edu.miu.waa.demoinclasslab1.entity.ExceptionLogger;
import edu.miu.waa.demoinclasslab1.entity.Logger;
import edu.miu.waa.demoinclasslab1.repo.LoggerRepo;
import edu.miu.waa.demoinclasslab1.service.ExceptionLoggerService;
import edu.miu.waa.demoinclasslab1.service.LoggerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Aspect
@Component
public class LoggerAspect {
    @Autowired
    LoggerService loggerService;
    @Autowired
    ExceptionLoggerService exceptionLoggerService;
    @Pointcut("@annotation(edu.miu.waa.demoinclasslab1.aspect.annotation.LogMe)")
    public void logMeAnnotion(){}

    @Pointcut("execution( * edu.miu.waa.demoinclasslab1.controller.UserController.*(..))")
    public void logMe() {  }

    @AfterReturning("logMe()")
    public void logAfterReturn(JoinPoint joinPoint) {
        Logger log = new Logger(UUID.randomUUID().toString(), LocalDate.now(), LocalTime.now(),"admin",joinPoint.getSignature().getName());
        loggerService.saveLog(log);
    }
    @AfterThrowing(value ="logMe()", throwing = "exception")
    public void logAfterThrow(JoinPoint joinPoint,Exception exception) {
        System.out.println("after throw");
        ExceptionLogger exceptionLogger = new ExceptionLogger(UUID.randomUUID().toString(), LocalDate.now(), LocalTime.now(),"admin",joinPoint.getSignature().getName(),exception.getClass().getName());
        exceptionLoggerService.saveExceptionLogger(exceptionLogger);
    }


}
