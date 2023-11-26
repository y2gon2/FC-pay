package dev.be.banking.application.port.in;

import dev.be.banking.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FirmBankingCommand extends SelfValidating<FirmBankingCommand> {
    @NotNull private final String fromBankName;
    @NotNull private final String fromBankAccountNumber;
    @NotNull private final String toBankName;
    @NotNull private final String toBankAccountNumber;
    @NotNull private final int moneyAmount;

    public FirmBankingCommand(
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            int moneyAmount
    ) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;

        this.validateSelf();
    }
}
