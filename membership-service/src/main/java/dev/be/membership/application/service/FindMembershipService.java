package dev.be.membership.application.service;

import dev.be.common.UseCase;
import dev.be.membership.adapter.out.persistence.MembershipJpaEntity;
import dev.be.membership.adapter.out.persistence.MembershipMapper;
import dev.be.membership.application.port.in.FindMembershipCommand;
import dev.be.membership.application.port.in.FindMembershipUseCase;
import dev.be.membership.application.port.out.FindMembershipPort;
import dev.be.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindMembershipService implements FindMembershipUseCase {

    private final FindMembershipPort findMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership findMembership(FindMembershipCommand command) {
        MembershipJpaEntity jpaEntity = findMembershipPort.findMembership(
                new Membership.MembershipId(command.getMembershipId())
        );

        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
