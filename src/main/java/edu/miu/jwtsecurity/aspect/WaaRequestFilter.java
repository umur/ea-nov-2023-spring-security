package edu.miu.jwtsecurity.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class WaaRequestFilter {

    private static final int MAX_BAD_WORDS_REQUESTS = 5;
    private static final int BAN_DURATION_MINUTES = 15;

    private Map<String, Integer> userRequestsCount = new HashMap<>();
    private Map<String, LocalDateTime> userBanEndTime = new HashMap<>();

    @Around("execution(* edu.miu.jwtsecurity.controller.*.*(..)) && args(text)")
    public Object filterRequests(ProceedingJoinPoint joinPoint, String text) throws Throwable {
        String username = getUsernameFromRequest(joinPoint);

        if (!isUserBanned(username)) {
            if (containsOffensiveWords(text)) {
                handleOffensiveRequest(username);
                throw new RuntimeException("Offensive words detected. Request rejected.");
            }
        } else {
            throw new RuntimeException("Max Bad Words Requests Limit has been Reached. You need to wait for "
                    + getRemainingBanTime(username) + " minutes.");
        }

        return joinPoint.proceed();
    }

    private String getUsernameFromRequest(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof String) {
            return (String) args[0];
        }
        return null;
    }

    private boolean containsOffensiveWords(String text) {
        return text.toLowerCase().contains("spring");
    }

    private void handleOffensiveRequest(String username) {
        int requestCount = userRequestsCount.getOrDefault(username, 0);
        userRequestsCount.put(username, requestCount + 1);

        if (requestCount + 1 >= MAX_BAD_WORDS_REQUESTS) {
            banUser(username);
        }
    }

    private void banUser(String username) {
        userBanEndTime.put(username, LocalDateTime.now().plusMinutes(BAN_DURATION_MINUTES));
        userRequestsCount.remove(username);
    }

    private boolean isUserBanned(String username) {
        LocalDateTime banEndTime = userBanEndTime.get(username);
        return banEndTime != null && LocalDateTime.now().isBefore(banEndTime);
    }

    private long getRemainingBanTime(String username) {
        LocalDateTime banEndTime = userBanEndTime.get(username);
        return banEndTime != null ? LocalDateTime.now().until(banEndTime, java.time.temporal.ChronoUnit.MINUTES) : 0;
    }
}
