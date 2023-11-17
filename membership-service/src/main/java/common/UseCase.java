package common;


import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
/**
 * 사용 예 ??
 * Service 구현에 사용될 예정
 * ex. query 요청으로 read data 으로 business logic 구현하는 예 ????
 * */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface UseCase {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
