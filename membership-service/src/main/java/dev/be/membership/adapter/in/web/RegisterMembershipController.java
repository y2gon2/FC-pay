package dev.be.membership.adapter.in.web;

import common.WebAdapter;
import dev.be.membership.application.port.in.RegisterMembershipCommand;
import dev.be.membership.application.port.in.RegisterMembershipUseCase;
import dev.be.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


// WebAdapter
@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {

    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping(path = "/membership/register")
    Membership registerMembership(
            @RequestBody RegisterMembershipRequest request
    ) {
        // request~~ 를 받아서
        // 해당 adapter 에서 request -> Command 로 변환하는 해서 전달 하는 이유
        // -> Service 구현체인 UseCase 가 외부에서 전달 받은 request 에 직접적인 영향을 받지 않도록 분리하는 작업이 필요
        //
        // 그러다면 command 는 뭘까?
        // input data -> adapter -> port  과정 중 port 에 해당!!
        // UseCase 에 command 보냄

        RegisterMembershipCommand command = RegisterMembershipCommand
                .builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isValid(true)
                .isCorp(request.isCorp())
                .build();

        return registerMembershipUseCase.registerMembership(command);
    }
}
