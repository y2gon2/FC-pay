package dev.be.membership.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMembershipRequest {
//    private String membershipId; -> 시스템 관리를 위한 자종 지정 값이므로 회원 등록으로 외부로부터 제공 받는 field 가 아님.
    private String name;
    private String address;
    private String email;
//    private  boolean isValid; -> 해당 data 는 logic 처리를 위한 내부 기능 구현을 위해서만 필요하고 외부 통신 작업에서는 필요가 없음.
    private boolean isCorp;
}
