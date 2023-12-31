package dev.be.banking.application.service;

import dev.be.banking.adapter.out.external.bank.BankAccount;
import dev.be.banking.adapter.out.external.bank.GetBankAccountRequest;
import dev.be.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import dev.be.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import dev.be.banking.application.port.in.RegisterBankAccountCommand;
import dev.be.banking.application.port.in.RegisterBankAccountUseCase;
import dev.be.banking.application.port.out.RegisterBankAccountPort;
import dev.be.banking.application.port.out.RequestBankAccountPort;
import dev.be.banking.common.UseCase;
import dev.be.banking.domain.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper mapper;
    private final RequestBankAccountPort requestBankAccountPort;

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        // 은행 계좌를 등록해야 하는 서비스

        // todo: membership 서비스에서 확인 작업
        command.getBankName();
        command.getBankAccountNumber();

        // 1. 등록된 계좌인지 확인한다.

        // 외부의 은행에 이 계좌가 정상적으로 등록되어 있는지를 확인
        // -> 외부 은행과 통신 필요
        // -> to be out of (Port -> Adapter ->) External System
        // -> 이에 필요한 port 필요

        BankAccount accountInfo = requestBankAccountPort
                .getBankAccountInfo(
                    new GetBankAccountRequest(
                            command.getBankName(),
                            command.getBankAccountNumber()
                    )
                );

        if (accountInfo.isValid()) {
            // True -> 계좌 등록(저장), 성공한 등록 정보 return
            return mapper.mapToDomainEntity(
                    registerBankAccountPort.createBankAccount(
                            new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                            new RegisteredBankAccount.BankName(command.getBankName()),
                            new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                            new RegisteredBankAccount.LinkedStatusIsValid(command.isLinkedStatusIsValid())
                    )
            );
        } else {
            return null;
        }
    }
}
