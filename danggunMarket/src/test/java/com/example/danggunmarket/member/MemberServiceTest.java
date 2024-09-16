package com.example.danggunmarket.member;

import com.example.danggunmarket.member.dto.JoinRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    void joinMember() {
        JoinRequest joinRequest = JoinRequest.builder()
                .email("ee22e@aaa.com")
                .name("asd")
                .nickname("aefaef22")
                .password("aggeaegeg44")
                .telNumber("010-2222-3333")
                .detail("104동 502호")
                .city("")
                .street("경인로 134번길 72")
                .build();

        memberService.isDuplicatedEmail(joinRequest.getEmail());
        memberService.isDuplicatedNickname(joinRequest.getNickname());
        memberService.join(joinRequest);
    }
}