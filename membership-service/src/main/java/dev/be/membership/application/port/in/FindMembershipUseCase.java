package dev.be.membership.application.port.in;

import dev.be.membership.domain.Membership;

public interface FindMembershipUseCase {
    Membership findMembership(FindMembershipCommand command);

//    Membership findAxonMembership(FindMembershipCommand command);
}
