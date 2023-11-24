package dev.be.banking.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.be.banking.domain.RegisteredBankAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Banking 서비스 test")
@SpringBootTest
@AutoConfigureMockMvc
class RegisterBankAccountControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;

    @DisplayName("은행 계좌 등록 test")
    @Test
    public void testRegisterBankingAccount() throws Exception {
        // Given
        RegisterBankAccountRequest request = new RegisterBankAccountRequest(
                "1",
                "hana",
                "123-456-789",
                true
        );

        RegisteredBankAccount bankAccount = RegisteredBankAccount
                .generateRegisteredBankAccount(
                  new RegisteredBankAccount.RegisteredBankAccountId("1"),
                  new RegisteredBankAccount.MembershipId("1"),
                  new RegisteredBankAccount.BankName("hana"),
                  new RegisteredBankAccount.BankAccountNumber("123-456-789"),
                  new RegisteredBankAccount.LinkedStatusIsValid(true)
                );

        // When & Then
        mockMvc.perform(
                MockMvcRequestBuilders.post("/bank_account/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((MockMvcResultMatchers.content().string(
                        mapper.writeValueAsString(bankAccount))
                ));

    }

}