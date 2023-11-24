package dev.be.banking.common;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * adapter.out.external 에서 외부 요청을 위한 사용자 정의 annotation
 * */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ExternalSystemAdapter {

    @AliasFor(annotation = Component.class)
    String value() default "";
}
