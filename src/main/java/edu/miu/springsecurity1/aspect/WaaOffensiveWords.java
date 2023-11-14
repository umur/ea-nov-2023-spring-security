package edu.miu.springsecurity1.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import java.lang.reflect.Field;
import java.util.*;

@Aspect
@Component
public class WaaOffensiveWords {

    private static final Map<String, Integer> userBadWordRequests = new HashMap<>();
    private static final Map<String, Long> userBanTime = new HashMap<>();
    private static final Set<String> offensiveWords = new HashSet<>(Arrays.asList("spring", "example", "other_bad_word"));
    private static final int MAX_BAD_WORD_REQUESTS = 5;
    private static final long BAN_TIME_MILLIS = 15 * 60 * 1000; // 15 minutes ban

    @Before("execution(* edu.miu.springsecurity1.controller.ProductController.*(..))")
    public void filterOffensiveWordsAndRequests(JoinPoint joinPoint) throws IllegalAccessException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Request Filtering
        if (userBanTime.containsKey(username) && System.currentTimeMillis() < userBanTime.get(username)) {
            throw new MaxRequestsExceededException(getRemainingBanTimeMessage(username));
        }


        if (userBadWordRequests.getOrDefault(username,0) > MAX_BAD_WORD_REQUESTS) {
            userBanTime.put(username, System.currentTimeMillis() + BAN_TIME_MILLIS);
            throw new MaxRequestsExceededException(getRemainingBanTimeMessage(username));
        }

        // Offensive Word Filtering
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String) {
                String inputValue = (String) args[i];
                args[i] = filterString(inputValue);
            } else if (args[i] != null) {
                inspectObjectAndFilter(args[i]);
            }
        }
    }

    private void inspectObjectAndFilter(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return;
        }

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            if (field.getType() == String.class) {
                String fieldValue = (String) field.get(obj);
                field.set(obj, filterString(fieldValue));
            } else if (!field.getType().isPrimitive()) {
                // Recursively inspect nested objects
                inspectObjectAndFilter(field.get(obj));
            }
        }
    }

    private String filterString(String value) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        for (String word : offensiveWords) {
            if (value != null && value.toLowerCase().contains(word)) {
                String replacement = "*".repeat(word.length());
                value = value.replaceAll("(?i)" + word, replacement);
                userBadWordRequests.put(username, userBadWordRequests.getOrDefault(username, 0) + 1);

            }
        }
        return value;
    }

    private String getRemainingBanTimeMessage(String username) {
        long remainingTime = (userBanTime.get(username) - System.currentTimeMillis()) / (60 * 1000);
        return "Max Bad Words Requests Limit has been Reached. You need to wait for " + remainingTime + " minutes.";
    }
}

 class MaxRequestsExceededException extends HttpStatusCodeException {
    public MaxRequestsExceededException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
