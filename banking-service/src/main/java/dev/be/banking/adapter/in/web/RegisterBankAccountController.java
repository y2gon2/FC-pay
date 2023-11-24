package dev.be.banking.adapter.in.web;

import dev.be.banking.application.port.in.RegisterBankAccountCommand;
import dev.be.banking.application.port.in.RegisterBankAccountUseCase;
import dev.be.banking.common.WebAdapter;
import dev.be.banking.domain.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping(path = "/bank_account/register")
    RegisteredBankAccount registeredBankAccount(
            @RequestBody RegisterBankAccountRequest request
    ) {
        RegisterBankAccountCommand command = RegisterBankAccountCommand
                .builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .linkedStatusIsValid(request.isLinkedStatusIsValid())
                .build();

        RegisteredBankAccount registeredBankAccount = registerBankAccountUseCase.registerBankAccount(command);

        if (registeredBankAccount == null) {
            // TODO : Error Handling
//            throw new RuntimeException("등록 실패 : 유효하지 않은 뱅킹 정보");
            return null;
        }

        return registeredBankAccount;
    }
}
