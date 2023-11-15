package dev.be.membership.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {

    @GetMapping(path = "/test")
    void test() {
        log.info("TEST");
    }
}
