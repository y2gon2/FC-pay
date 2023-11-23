package dev.be.membership;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.be.membership.adapter.in.web.RegisterMembershipRequest;
import dev.be.membership.domain.Membership;
import dev.be.membership.adapter.in.web.ModifyMembershipRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// @RunWith(SpringRunner.class) // Junit4 용 필요없음
//
// @ExtendWith(SpringExtension.class) 의 필요성
// Spring Context 사용: @SpringBootTest 어노테이션과 함께 사용하여, 전체 Spring 애플리케이션 컨텍스트를 로드
//                    이를 통해 MockMvc, ObjectMapper 등의 Spring 빈을 테스트에서 사용할 수 있습니다.
// MockMvc 통합: @AutoConfigureMockMvc와 함께 사용하여, Spring MVC 테스트를 위한 MockMvc 인스턴스를 자동 구성
//              이를 통해 컨트롤러 레이어의 테스트를 수행할 수 있습니다.
//@ExtendWith(SpringExtension.class) // JUnit 5와 Spring Boot 2.x 이상 (이미 @SpringBootTest 에 포함되어 있음. )
@SpringBootTest
@AutoConfigureMockMvc
public class RegisterMembershipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @DisplayName("신규 회원 등록")
    @Test
    public void testRegisterMembership() throws Exception {
        // Given
        RegisterMembershipRequest request = new RegisterMembershipRequest(
                "name",
                "address",
                "email",
                false
        );

        Membership membership = Membership
                .generateMember(
                        new Membership.MembershipId("2"),
                        new Membership.MembershipName("name"),
                        new Membership.MembershipEmail("email"),
                        new Membership.MembershipAddress("address"),
                        new Membership.MembershipIsValid(true),
                        new Membership.MembershipIsCorp(false)
                );

        // When & Then
        mockMvc.perform(
                MockMvcRequestBuilders.post("/membership/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(membership)));
    }

//    @Disabled(".. test 실패 ")
    @DisplayName("회원 정보 수정")
    @Test
    public void testModifyMembership() throws Exception {
        // Given
        ModifyMembershipRequest modified_request = new ModifyMembershipRequest(
                "1",
                "modified_name",
                "modified_address",
                "modified_email",
                true,
                true
        );

        Membership expectation = Membership
                .generateMember(
                        new Membership.MembershipId("1"),
                        new Membership.MembershipName("modified_name"),
                        new Membership.MembershipEmail("modified_email"),
                        new Membership.MembershipAddress("modified_address"),
                        new Membership.MembershipIsValid(true),
                        new Membership.MembershipIsCorp(true)
                );


        // When & Then
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/membership/modify/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(modified_request))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(expectation)));
    }
}
