package dev.be.banking.application.service;

import dev.be.banking.adapter.out.external.bank.ExternalFirmBankingRequest;
import dev.be.banking.adapter.out.external.bank.FirmBankingResult;
import dev.be.banking.adapter.out.persistence.FirmBankingJpaEntity;
import dev.be.banking.adapter.out.persistence.FirmBankingMapper;
import dev.be.banking.application.port.in.FirmBankingCommand;
import dev.be.banking.application.port.in.FirmBankingUseCase;
import dev.be.banking.application.port.out.RequestExternalFirmBankingPort;
import dev.be.banking.application.port.out.RequestFirmBankingPort;
import dev.be.banking.common.UseCase;
import dev.be.banking.domain.FirmBankingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FirmBankingService implements FirmBankingUseCase {

    private final RequestFirmBankingPort requestFirmBankingPort;
    private final FirmBankingMapper mapper;
    private final RequestExternalFirmBankingPort requestExternalFirmBankingPort;

    @Override
    public FirmBankingRequest requestFirmBanking(FirmBankingCommand command) {

        // Business Logic
        // a 계좌 -> b 계좌로 돈을 보냄

        // 1. 요청에 대해 정보를 먼저 write "요청 status : 0" 상태로
        FirmBankingJpaEntity entity  = requestFirmBankingPort
                .createFirmBankingRequest(
                    new FirmBankingRequest.FromBankName(command.getFromBankName()),
                    new FirmBankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                    new FirmBankingRequest.ToBankName(command.getToBankName()),
                    new FirmBankingRequest.ToBankAccount(command.getToBankAccountNumber()),
                    new FirmBankingRequest.MoneyAmount(command.getMoneyAmount()),
                    new FirmBankingRequest.FirmBankingStatus(0)
                );

        // 2. 외부 은행에 펌뱅킹 요청
        FirmBankingResult ready = requestExternalFirmBankingPort
                .requestExternalFirmBanking(
                    new ExternalFirmBankingRequest(
                            command.getToBankName(),
                            command.getFromBankAccountNumber(),
                            command.getToBankName(),
                            command.getToBankAccountNumber()
                ));

        // Transaction UUID 값을 저장 -> Transaction 의 Debugging 처리시 필요한 고유값을 생성 및 저장
        UUID randomUUID = UUID.randomUUID();
        entity.setUuid(randomUUID.toString());

        // 3. 결과에 따라서 1번에서 작성한 FirmBankingRequest 정보를 update
        if (ready.getResultCode() == 0) {
            // 성공
            entity.setFirmBankingStatus(1);
        } else {
            // 실패
            entity.setFirmBankingStatus(2);
        }

        // 4. 바뀐 상태 값을 기준으로 다시 sava
        FirmBankingJpaEntity result = requestFirmBankingPort
                .modifyFirmBankingRequest(entity);

        // 5. 결과 반환
        return mapper.mapToDomainEntity(result, randomUUID);
    }
}
