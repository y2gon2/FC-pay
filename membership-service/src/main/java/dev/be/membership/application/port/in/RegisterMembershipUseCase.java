package dev.be.membership.application.port.in;

import dev.be.membership.domain.Membership;

// by ChatGPT
// Application Port의 역할
// 계약 정의: Port는 비즈니스 로직과 외부 요소 사이의 계약을 정의합니다.
// 이 계약은 인터페이스로 표현되며, 어떤 데이터가 들어오고 나가는지, 어떤 작업을
// 수행할 수 있는지를 명시합니다.
//
// 분리와 추상화: 인터페이스를 사용함으로써 비즈니스 로직이 구체적인 외부 구현으로부터 분리됩니다.
// 이는 비즈니스 로직이 특정 기술이나 외부 시스템에 의존하지 않도록 보장합니다.
//
// 교체 용이성: 인터페이스를 통해 구현체를 쉽게 교체할 수 있습니다.
// 예를 들어, 데이터베이스 시스템을 변경하거나, 다른 외부 서비스를 사용할 때
// 기존 비즈니스 로직을 수정하지 않고도 새로운 구현을 플러그할 수 있습니다.
public interface RegisterMembershipUseCase {

    /**
     * command : 회원 가입 요청 (밖 -> 안) 명령
     * 구현체   : 회원 가입 business logic 을 구성
     */
    Membership registerMembership(RegisterMembershipCommand command);
}
