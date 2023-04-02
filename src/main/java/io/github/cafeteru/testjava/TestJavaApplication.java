package io.github.cafeteru.testjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TestJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestJavaApplication.class, args);
    }

}
