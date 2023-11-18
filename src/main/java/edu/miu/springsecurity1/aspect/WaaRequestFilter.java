package edu.miu.springsecurity1.aspect;

import edu.miu.springsecurity1.service.impl.AwesomeUserDetails;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Aspect
@Order(0)
@Component
@RequiredArgsConstructor
public class WaaRequestFilter {

    @Autowired
    private Map<String, List<Long>> blackListInfoMap;


    @Around("execution(* edu.miu.springsecurity1.controller..*.*(..))")
    public Object blackListControl(ProceedingJoinPoint joinPoint) throws Throwable {
        var userDetails = (AwesomeUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var username =  userDetails.getUsername();
        var info = blackListInfoMap.get(username);
        if (info != null) {
            if (last30MinCount(info) == 5) {
                var dif = System.currentTimeMillis() - info.get(4);
                if (dif > 900000L) {
                    blackListInfoMap.remove(username);
                } else {
                    var minutes = (dif/1000)/60;
                    return new ResponseEntity<>("Max Bad Words Requests Limit has been Reached. You need wait for " +  (15 - minutes) + " minutes",
                            HttpStatus.BAD_REQUEST);
                }
            }
        }
        return joinPoint.proceed();
    }

    private int last30MinCount(List<Long> info) {
        return (int) info.stream()
                .filter(x -> System.currentTimeMillis() - x <= 1800000)
                .count();
    }

}
