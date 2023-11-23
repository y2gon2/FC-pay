package dev.be.membership.application.port.in;


import dev.be.banking.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FindMembershipCommand extends SelfValidating<FindMembershipCommand> {
    @NotNull
    @NotBlank
    private final String membershipId;

    public FindMembershipCommand(String membershipId) {
        this.membershipId = membershipId;

        this.validateSelf();
    }
}
