package io.github.cafeteru.apiumhubexam.infrastructure.aspects;

import static io.github.cafeteru.apiumhubexam.util.DateConverter.stringToLocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LocalDateTimeAspect {

    @Around(value = "within(@org.springframework.web.bind.annotation.RestController * )" +
        "&& within(io.github.cafeteru.apiumhubexam..*)" +
        "&& args(localDateTimeString, ..)")
    public Object validateLocalDateTime(ProceedingJoinPoint joinPoint, String localDateTimeString) throws Throwable {
        try {
            stringToLocalDateTime(localDateTimeString);
            return joinPoint.proceed();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid LocalDateTime: " + localDateTimeString, e);
        }
    }
}