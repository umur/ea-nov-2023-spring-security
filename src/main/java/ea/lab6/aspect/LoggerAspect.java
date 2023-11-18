package ea.lab6.aspect;


import ea.lab6.dto.ProductDto;
import ea.lab6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggerAspect {

    private final UserService userService;
    private static final Map<String, List<Date>> userLastRequestTimestamp = new HashMap<>();
    private static final Map<String, Date> banUsers = new HashMap<>();

    private final Integer REQUESTS_COUNT_PERIOD = 3; // 30 minutes in milliseconds
    private final Integer BAN_DURATION = 2;
    private final Integer TOTAL_BAD_REQUEST = 5;

    @Pointcut("@annotation(ea.lab6.aspect.annotation.LogMe)")
    public void logMeAnnotation() {

    }

    @Before(" logMeAnnotation()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Log before the method: " + joinPoint.getSignature().getName());
    }

    @Around("execution(* ea.lab6.service.ProductService.addProduct(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String email = userService.getUserEmailFromSpringContext();
        Date banStartDate = banUsers.get(email);
        if (banStartDate != null) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, -1 * BAN_DURATION);
            if (banStartDate.before(cal.getTime())) {
                System.out.println("Ban Date" + banStartDate);
                System.out.println("Current Date" + new Date());
                System.out.println("Ban removed");
                banUsers.remove(email);
            } else {
                Long waitingValue = (cal.getTimeInMillis() - banStartDate.getTime()) / 1000;
                System.out.println("Ban Date" + banStartDate);
                System.out.println("Current Date" + new Date());
                throw new RuntimeException("Max Bad Words Requests Limit has been Reached. You need to wait for " +
                        waitingValue + " seconds.");
            }


        }
        System.out.println("logAround( addProduct) is running!");
        System.out.println(" method : " + joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();

        ProductDto productDto = (ProductDto) args[0];
        filterBadWords(productDto.getName());
        System.out.println(" Product name : " + productDto.getName());

        System.out.println("Around before is running!");
        Object result = joinPoint.proceed(); //continue on the intercepted method
        System.out.println("Around after is running!");

        System.out.println("******");
        return result;
    }


    private String filterBadWords(String input) throws Exception {

        String[] badWords = {"spring", "example", "other"};
        String newInput = input;
        for (String badWord : badWords) {
            newInput = newInput.replaceAll(badWord, "*".repeat(badWord.length()));


        }

        if (!newInput.equals(input)) {
            System.out.println("Input " + input);
            System.out.println("Output " + newInput);
            addBadRequest();
            input = newInput;
        }
        System.out.println("Input " + input);
        System.out.println("Output " + newInput);
        return input;
    }

    private void addBadRequest() throws Exception {
        String email = userService.getUserEmailFromSpringContext();
        List<Date> dates = userLastRequestTimestamp.get(email);
        if (dates == null)
            dates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -1 * REQUESTS_COUNT_PERIOD);

       dates = dates.stream().filter(d -> !d.before(cal.getTime())).collect(Collectors.toList());

        dates.add(new Date());
        userLastRequestTimestamp.put(email,dates);
        if (dates.size() > TOTAL_BAD_REQUEST) {
            System.out.println("Add request ban " + new Date());
            banUsers.put(email, new Date());
            userLastRequestTimestamp.remove(email);
        } else {
            userLastRequestTimestamp.put(email, dates);
            System.out.println("Add bad request " + new Date() +" bad count " + userLastRequestTimestamp.get(email).size());
        }


    }


}
