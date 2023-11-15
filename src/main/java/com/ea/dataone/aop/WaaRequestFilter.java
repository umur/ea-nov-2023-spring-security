package com.ea.dataone.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Aspect
@Component
public class WaaRequestFilter {

    private static final List<String> OFFENSIVE_WORDS = Arrays.asList("spring", "example", "other");

    //@Autowired
    //private RedisTemplate<String, Integer> redisTemplate;
    private static final Map<String, Integer> requestCountMap = new ConcurrentHashMap<>();
    private static final Map<String, Long> banMap = new ConcurrentHashMap<>();

    @Pointcut("execution(* com.ea.dataone.service.ProductService.*(..)) && args(userId, request)")
    public void processRequestPointcut(String userId, String request) {}

    @Around("processRequestPointcut(userId, request)")
    public Object filterRequests(ProceedingJoinPoint joinPoint, String userId, String request) throws Throwable {
        if (isUserBanned(userId)) {
            long remainingTime = getRemainingBanTime(userId);
            throw new RuntimeException("Max Bad Words Requests Limit has been Reached. You need to wait for " + remainingTime + " minutes.");
        }

        // Check for offensive words in the filtered request
        if (containsOffensiveWord(request)) {
            incrementBadWordCount(userId);
            if (getBadWordCount(userId) > 5) {
                setBan(userId);
                throw new RuntimeException("Max Bad Words Requests Limit has been Reached. You need to wait for " + getRemainingBanTime(userId) + " minutes.");
            }
        }

        return joinPoint.proceed(new Object[]{userId, request});
    }

    private boolean containsOffensiveWord(String input) {
        for (String word : OFFENSIVE_WORDS) {
            // Use case-insensitive pattern for matching
            if (Pattern.compile("(?i)" + word).matcher(input).find()) {
                return true;
            }
        }
        return false;
    }

    private void incrementBadWordCount(String userId) {
        requestCountMap.merge(userId, 1, Integer::sum);
    }

    private int getBadWordCount(String userId) {
        return requestCountMap.getOrDefault(userId, 0);
    }

    private void setBan(String userId) {
        banMap.put(userId, System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15));
    }

    private boolean isUserBanned(String userId) {
        Long banEndTime = banMap.get(userId);
        return banEndTime != null && banEndTime > System.currentTimeMillis();
    }

    private long getRemainingBanTime(String userId) {
        Long banEndTime = banMap.get(userId);
        if (banEndTime != null) {
            return TimeUnit.MILLISECONDS.toMinutes(banEndTime - System.currentTimeMillis());
        }
        return 0;
    }
}
