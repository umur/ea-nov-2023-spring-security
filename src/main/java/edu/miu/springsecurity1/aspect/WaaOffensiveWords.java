package edu.miu.springsecurity1.aspect;

import edu.miu.springsecurity1.service.impl.AwesomeUserDetails;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

@Aspect
@Order(1)
@Component
@RequiredArgsConstructor
public class WaaOffensiveWords {

    private final Set<String> badWords = new HashSet<>(Arrays.asList("bad", "ugly", "useless"));
    private final Map<String, List<Long>> blackListInfoMap;

    @Around("execution(* edu.miu.springsecurity1.controller..*.*(..))")
    public Object offensiveControl(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            Field[] fields = arg.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                var fieldVal = field.get(arg);
                if (fieldVal instanceof String) {
                    var fieldValStr = (String) fieldVal;
                    boolean isDetected = false;
                    for (String badWord : badWords) {
                        if (fieldValStr.contains(badWord)) {
                            isDetected = true;
                            while (fieldValStr.contains(badWord)) {
                                fieldValStr = fieldValStr.replaceAll(badWord, "*".repeat(badWord.length()));
                            }
                        }
                    }
                    if(isDetected) {
                        var userDetails = (AwesomeUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        var username =  userDetails.getUsername();
                        if (blackListInfoMap.containsKey(username)) {
                            var currentInfo = blackListInfoMap.get(username);
                            currentInfo.add(System.currentTimeMillis());
                            blackListInfoMap.put(username, currentInfo);
                        } else {
                            List<Long> l = new ArrayList<>();
                            l.add(System.currentTimeMillis());
                            blackListInfoMap.put(username, l);
                        }
                    }
                    field.set(arg, fieldValStr);
                }
            }
        }


        return joinPoint.proceed(args);
    }

}
