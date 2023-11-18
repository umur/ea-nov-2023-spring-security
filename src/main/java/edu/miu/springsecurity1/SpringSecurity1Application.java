package edu.miu.springsecurity1;

import edu.miu.springsecurity1.service.impl.AwesomeUserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringSecurity1Application {


    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity1Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Map<String, List<Long>> blackListInfoMap(){
        return new HashMap<>();
    };


}
