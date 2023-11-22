package dev.be.membership.application.service;

import dev.be.common.UseCase;
import dev.be.membership.adapter.out.persistence.MembershipJpaEntity;
import dev.be.membership.adapter.out.persistence.MembershipMapper;
import dev.be.membership.application.port.in.RegisterMembershipCommand;
import dev.be.membership.application.port.in.RegisterMembershipUseCase;
import dev.be.membership.application.port.out.RegisterMembershipPort;
import dev.be.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


// Service Class가 Interface를 상속하는 이유 by ChatGPT
// 유연성: 인터페이스를 상속함으로써, Service Class는 특정 Port의 계약을 충족시키게 됩니다.
// 이렇게 하면 나중에 다른 구현으로 쉽게 교체할 수 있으며, 테스트 및 유지보수가 용이해집니다.
//
// 결합도 감소: Service Class가 직접적으로 외부 요소들과 상호작용하지 않고 인터페이스를 통해
// 상호작용함으로써, 결합도가 감소합니다. 이는 코드의 유연성과 재사용성을 증가시킵니다.
//
// 테스트 용이성: 인터페이스를 사용하면 Mocking이나 Stubbing을 통해 테스트가 용이해집니다.
// 실제 외부 시스템에 의존하지 않고도 비즈니스 로직을 효과적으로 테스트할 수 있습니다.
@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterMembershipService implements RegisterMembershipUseCase {

    // 해당 port 를 통해서 data 를 밖(DB)으로 내보냄
    // 즉 해당 port 를 business logic 에서 적극적으로 사용하여 port 의 구현체인 adapter 에서
    // 외부인 DB 와의 통신을 위해 repository 를 사용하게 됨.
    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
        // command -> biz logic -> (part -> adapter) -> DB
        MembershipJpaEntity jpaEntity = registerMembershipPort.createMembership(
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp())
        );

        // entity -> Membership
        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
