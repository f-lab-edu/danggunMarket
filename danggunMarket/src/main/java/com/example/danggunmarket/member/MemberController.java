package com.example.danggunmarket.member;

import com.example.danggunmarket.member.dto.JoinRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/v1/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody JoinRequest joinRequest) {
        memberService.join(joinRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/check/email")
    public ResponseEntity<Void> checkExistEmail(@RequestParam String email) {
        memberService.isDuplicatedEmail(email);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/check/nickname")
    public ResponseEntity<Void> checkExistNickname(@RequestParam String nickname) {
        memberService.isDuplicatedEmail(nickname);

        return ResponseEntity.ok().build();
    }

}
