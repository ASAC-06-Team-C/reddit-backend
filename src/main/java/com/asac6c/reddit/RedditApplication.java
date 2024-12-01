package com.asac6c.reddit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// exclude : 비활성화한 의존성
@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RedditApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(RedditApplication.class, args);
        } catch (Exception e) {
            log.error(e.getMessage(), e + "여기서 무슨문제야");
        }

    }

}
