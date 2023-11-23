package dev.be.membership.application.service;

import dev.be.banking.common.UseCase;
import dev.be.membership.adapter.out.persistence.MembershipJpaEntity;
import dev.be.membership.adapter.out.persistence.MembershipMapper;
import dev.be.membership.application.port.in.ModifyMembershipUseCase;
import dev.be.membership.application.port.out.ModifyMembershipPort;
import dev.be.membership.domain.Membership;
import dev.be.membership.application.port.in.ModifyMembershipCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ModifiyMembershipService implements ModifyMembershipUseCase {

    private final ModifyMembershipPort modifyMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership modifyMembership(ModifyMembershipCommand command) {
        MembershipJpaEntity jpaEntity = modifyMembershipPort.modifyMembership(
                new Membership.MembershipId(command.getMembershipId()),
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp())
        );

        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
