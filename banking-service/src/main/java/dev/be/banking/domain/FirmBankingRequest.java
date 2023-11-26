package dev.be.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FirmBankingRequest {
    @Getter private final String firmBankingRequestId;
    @Getter private final String fromBankName;
    @Getter private final String fromBankAccountNumber;
    @Getter private final String toBankName;
    @Getter private final String toBankAccountNumber;
    @Getter private final int moneyAmount;
    @Getter private final int firmBankingStatus; // 0: 요청, 1: 완료, 2: 실패
    @Getter private final UUID uuid;

    public static FirmBankingRequest generateFirmBankingRequest (
        FirmBankingRequest.FirmBankingRequestId firmBankingRequestId,
        FirmBankingRequest.FromBankName fromBankName,
        FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber,
        FirmBankingRequest.ToBankName toBankName,
        FirmBankingRequest.ToBankAccount toBankAccount,
        FirmBankingRequest.MoneyAmount moneyAmount,
        FirmBankingRequest.FirmBankingStatus firmBankingStatus,
        UUID uuid
    ) {
        return new FirmBankingRequest(
                firmBankingRequestId.getFirmBankingRequestId(),
                fromBankName.getFromBankName(),
                fromBankAccountNumber.getFromBankAccountNumber(),
                toBankName.getToBankName(),
                toBankAccount.getToBankAccount(),
                moneyAmount.getMoneyAmount(),
                firmBankingStatus.getFirmBankingStatus(),
                uuid
        );
    }

    @Value
    public static class FirmBankingRequestId {
        String firmBankingRequestId;

        public FirmBankingRequestId(String value) {
            this.firmBankingRequestId = value;
        }
    }

    @Value
    public static class FromBankName {
        String fromBankName;

        public FromBankName(String value) {
            this.fromBankName = value;
        }
    }

    @Value
    public static class FromBankAccountNumber {
        String fromBankAccountNumber;

        public FromBankAccountNumber(String value) {
            this.fromBankAccountNumber = value;
        }
    }

    @Value
    public static class ToBankName {
        String toBankName;

        public ToBankName(String value) {
            this.toBankName = value;
        }
    }

    @Value
    public static class ToBankAccount {
        String toBankAccount;

        public ToBankAccount(String value) {
            this.toBankAccount = value;
        }
    }

    @Value
    public static class MoneyAmount {
        int moneyAmount;

        public MoneyAmount(int value) {
            this.moneyAmount = value;
        }
    }

    @Value
    public static class FirmBankingStatus {
        int firmBankingStatus;

        public FirmBankingStatus(int value) {
            this.firmBankingStatus = value;
        }
    }

}
