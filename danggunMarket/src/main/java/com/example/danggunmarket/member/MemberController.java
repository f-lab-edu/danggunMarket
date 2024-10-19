package com.example.danggunmarket.member;

import com.example.danggunmarket.common.jwt.JwtUtil;
import com.example.danggunmarket.member.dto.JoinRequest;
import com.example.danggunmarket.member.dto.LoginRequest;
import com.example.danggunmarket.member.dto.LoginResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @PostMapping("/v1/members")
    public ResponseEntity<Void> signup(@Valid @RequestBody JoinRequest joinRequest) {
        memberService.join(joinRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/members/check-email")
    public ResponseEntity<Void> checkExistEmail(@RequestParam String email) {
        memberService.isDuplicatedEmail(email);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/members/check-nickname")
    public ResponseEntity<Void> checkExistNickname(@RequestParam String nickname) {
        memberService.isDuplicatedEmail(nickname);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/v1/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        LoginResponse loginResponse = memberService.login(loginRequest);

        response.addHeader("Authorization", "Bearer " + jwtUtil.createToken(loginResponse.getEmail()));

        return ResponseEntity.ok(loginResponse);
    }

}
