package com.example.springsequrity.aspect;



import com.example.springsequrity.service.ProductService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    @Autowired
    ProductService productService;

    @Pointcut("@annotation(com.example.springsequrity.aspect.annotation.LogAll)")
    public void logMeAnnotation(){

    }

    @Before(" logMeAnnotation()")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("Log before the method: " + joinPoint.getSignature().getName());
    }







}
