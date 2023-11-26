package dev.be.banking.application.port.in;

import dev.be.banking.domain.FirmBankingRequest;

public interface FirmBankingUseCase {
    FirmBankingRequest requestFirmBanking(FirmBankingCommand command);
}
