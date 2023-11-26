package dev.be.banking.adapter.in.web;

import dev.be.banking.application.port.in.FirmBankingCommand;
import dev.be.banking.application.port.in.FirmBankingUseCase;
import dev.be.banking.common.WebAdapter;
import dev.be.banking.domain.FirmBankingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmBankingController {
    private final FirmBankingUseCase firmBankingUseCase;

    @PostMapping("/banking/firmbanking/account/")
    FirmBankingRequest firmBankingRequest(
        @RequestBody RequestFirmBankingRequest request
    ) {
        FirmBankingCommand command = FirmBankingCommand
                .builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .toBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAmount(request.getMonyAmount())
                .build();

        return firmBankingUseCase.requestFirmBanking(command);
    }
}
