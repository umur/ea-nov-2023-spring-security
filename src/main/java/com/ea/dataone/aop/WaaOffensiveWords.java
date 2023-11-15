package com.ea.dataone.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class WaaOffensiveWords {

    private static final List<String> OFFENSIVE_WORDS = Arrays.asList("spring", "example", "other");

    @Around("execution(* com.ea.dataone.service.ProductService.*(..)) && args(request)")
    public Object filterOffensiveWords(ProceedingJoinPoint joinPoint, String request) throws Throwable {
        String filteredRequest = filterOffensiveWords(request);
        return joinPoint.proceed(new Object[]{filteredRequest});
    }

    private String filterOffensiveWords(String input) {
        for (String word : OFFENSIVE_WORDS) {
            input = input.replaceAll("(?i)" + word, "******");
        }
        return input;
    }
}