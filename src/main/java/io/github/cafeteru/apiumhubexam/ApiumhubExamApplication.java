package io.github.cafeteru.apiumhubexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ApiumhubExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiumhubExamApplication.class, args);
    }

}
