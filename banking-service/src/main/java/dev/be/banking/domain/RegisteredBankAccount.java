package dev.be.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {

    @Getter private final String registeredBankAccountId;
    @Getter private final String membershipId;
    @Getter private final String bankName; // enum 으로 추후 대체
    @Getter private final String bankAccountNumber;
    @Getter private final boolean linkedStatusIsValid;

    public static RegisteredBankAccount generateRegisteredBankAccount (
            RegisteredBankAccount.RegisteredBankAccountId registeredBankAccountId,
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid
    ) {
        return new RegisteredBankAccount(
                registeredBankAccountId.getRegisteredBankAccountId(),
                membershipId.getMembershipId(),
                bankName.getBankName(),
                bankAccountNumber.getBankAccountNumber(),
                linkedStatusIsValid.isLinkedStatusIsValid()
        );
    }

    @Value
    public static class RegisteredBankAccountId {
        String registeredBankAccountId;
        public RegisteredBankAccountId(String value) { this.registeredBankAccountId = value; }
    }

    @Value
    public static class MembershipId {
        String membershipId;
        public MembershipId(String value) { this.membershipId = value; }
    }

    @Value
    public static class BankName {
        String bankName;
        public BankName(String value) { this.bankName = value; }
    }

    @Value
    public static class BankAccountNumber {
        String bankAccountNumber;
        public BankAccountNumber(String value) { this.bankAccountNumber = value; }
    }

    @Value
    public static class LinkedStatusIsValid {
        boolean linkedStatusIsValid;
        public LinkedStatusIsValid(boolean value) { this.linkedStatusIsValid = value; }
    }

}
