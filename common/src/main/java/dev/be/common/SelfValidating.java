package dev.be.common;

import jakarta.validation.*;
import java.util.Set;

// 이 추상 class 내 method 를 구현하는 class 의 멤버들을 self validating 하기 위한 추상 class
public abstract class SelfValidating<T> {

    private Validator validator;

    public SelfValidating() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * by ChatGPT
     * 이 SelfValidating 클래스에서 validator.validate(); 메소드는 Bean Validation API를 사용하여
     * 객체의 속성에 대한 유효성 검사를 수행합니다. Bean Validation API는 Java EE 표준의 일부로,
     * 객체 모델의 유효성을 검증하기 위한 표준 방법을 제공합니다.
     *
     * validator.validate() 메소드는 다음과 같은 작업을 수행합니다:
     *
     * 속성에 대한 유효성 검사: 이 메소드는 호출된 객체 (this)의 속성에 대한 유효성을 검사합니다.
     * 객체의 각 속성은 클래스 정의에서 지정된 제약 조건(annotations)을 통해 유효성 규칙을 가질 수 있습니다.
     *
     * 유효성 검사 규칙: 유효성 검사의 규칙은 주로 애너테이션을 통해 정의됩니다. 예를 들어,
     * `@NotNull, @Size(min=1, max=10), @Email 등의 애너테이션을 사용하여 속성 값의 유효성을 검사할 수 있습니다.
     * 이러한 애너테이션들은 속성이 특정 조건을 충족해야 한다는 규칙을 정의합니다.
     *
     * 결과 처리: validate 메소드는 ConstraintViolation<T> 객체의 집합을 반환합니다. 각 ConstraintViolation 객체는
     * 유효성 검사 실패에 대한 세부 정보를 담고 있습니다. 만약 반환된 집합이 비어 있지 않다면 (!violations.isEmpty()),
     * 이는 하나 이상의 유효성 검사 실패를 의미합니다.
     *
     * 예외 처리: 유효성 검사에서 실패한 경우 (violations 집합이 비어 있지 않은 경우), ConstraintViolationException
     * 예외가 발생합니다. 이 예외는 유효성 검사 실패에 대한 세부 정보를 포함하고 있으며, 일반적으로 이를 적절히 처리하여
     * 사용자에게 유효하지 않은 데이터에 대해 알립니다.
     *
     * 즉, SelfValidating 클래스는 자체 유효성 검사 로직을 내장하여, 객체가 자신의 상태를 스스로 검증할 수 있도록 설계된 것입니다.
     * 이를 통해 객체가 다른 컨텍스트로 전달되기 전에 유효한 상태인지를 보장할 수 있습니다.
     * */
    protected void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
