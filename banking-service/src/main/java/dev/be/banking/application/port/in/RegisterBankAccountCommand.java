package dev.be.banking.application.port.in;

import dev.be.banking.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterBankAccountCommand extends SelfValidating<RegisterBankAccountCommand> {
    @NotNull private final String membershipId;
    @NotNull private final String bankName;
    @NotNull private final String bankAccountNumber;
    private final boolean linkedStatusIsValid;

    public RegisterBankAccountCommand(
            String membershipId,
            String bankName,
            String bankAccountNumber,
            boolean linkedStatusIsValid
    ) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;

        this.validateSelf();
    }
}
