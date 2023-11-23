package dev.be.banking.common;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * adapter.in.web 내부에 구현된 controller 의 component 생성을 위한 사용자 정의 annotation
 *
 * 사용자 정의 annotation 생성
 * `@Component 를 확장하여 다음의 기능을 부여한 annotation 을 정의함. (동일하게 runtime lifecycle 을 가짐.)
 * 1. Spring component 로 인식
 * 2.별칭 기능 제공 : `@Component 와 동일한 속성 (value) 를 사용
 * 3.문서화 지원 : JavaDoc에 문서화될 때 해당 어노테이션 정보도 함께 표시
 * */
@Target({ElementType.TYPE}) // class, intereface, enum, annotation 에 적용될 수 있음을 의미
@Retention(RetentionPolicy.RUNTIME) // 해당 annotation 정보를 어느 시점까지 유지할 것인가를 결정. 런타임 중에도 어노테이션 정보가 유지되고, 리플렉션을 통해 접근 가능하게 함을 의미
@Documented // 문서화의 목적으로만 사용되며, 어노테이션의 기능적인 측면에는 영향 없음. 어노테이션이 적용된 사용자 정의 어노테이션은 JavaDoc 생성 시 해당 어노테이션이 적용된 요소와 함께 문서화
@Component  // 해당 @WebAdapter 를 사용한 class 는 spring container 에 의해 관리되는 component 임을 의미, 의존성 주입, lifecycle 관리 등 편의성 제공
public @interface WebAdapter { // 사용자 정의 anntation 선언
    @AliasFor(annotation = Component.class) // Component.class 의 속성과 동일함을 의미
    String value() default "";              // 따라서, 해당 annotation 구현에 정의되 속성 String value() default ""; 을 동일하게 작성하여 일관성을 유지함.
}
