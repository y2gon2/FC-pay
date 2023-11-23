package dev.be.membership.application.port.in;

import dev.be.banking.common.SelfValidating;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterMembershipCommand extends SelfValidating<RegisterMembershipCommand> {

    // field 제한 조건을 선언적으로 만들어 주고
    @NotNull private final String name;

    @NotNull private final String email;

    @NotNull @NotBlank private final String address;

    @AssertTrue private final boolean isValid;

    private final boolean isCorp;

    public RegisterMembershipCommand(
            String name,
            String email,
            String address,
            boolean isValid,
            boolean isCorp
    ) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.isValid = isValid;
        this.isCorp = isCorp;

        this.validateSelf();
        // 이 method 로 하여금 이 class 에 포함된 constraints (ex. @NotNull)들을 검사해준다.
        // 예를 들면 해당 validateSelf() 사용하지 않았다면,
        //
        // if name == null {
        //      throw new IllegalArgumentException("name must not be null");
        // }
        // 과 같은 조건문을 사용했을 수도 있다.
        //
        // 그러나 해당 추상 함수를 사용하고,  @NotNull 과 같은 annotation 을 적용하면,
        // 동일 constraints (NotNull) 에 대해서 일괄 유효성 검사을 실행해 준다.

    }
}
