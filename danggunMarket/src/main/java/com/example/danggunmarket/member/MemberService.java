package com.example.danggunmarket.member;

import com.example.danggunmarket.member.dto.JoinRequest;
import com.example.danggunmarket.member.dto.LoginRequest;
import com.example.danggunmarket.member.dto.LoginResponse;
import com.example.danggunmarket.member.exception.AlreadyExistException;
import com.example.danggunmarket.member.exception.MemberErrorCode;
import com.example.danggunmarket.member.exception.MemberNotFoundException;
import com.example.danggunmarket.member.exception.WrongPasswordException;
import com.example.danggunmarket.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(JoinRequest joinRequest) {
        String encodePassword = passwordEncoder.encode(joinRequest.getPassword());
        joinRequest.setEncodePassword(encodePassword);

        MemberEntity member = MemberMapper.INSTANCE.toEntity(joinRequest);

        memberRepository.save(member);

    }

    public void isDuplicatedEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new AlreadyExistException(MemberErrorCode.ALREADY_EXIST_NICKNAME);
        }
    }

    public void isDuplicatedNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new AlreadyExistException(MemberErrorCode.ALREADY_EXIST_NICKNAME);
        }
    }

    public LoginResponse login(LoginRequest loginRequest) {
        MemberEntity member = memberRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new MemberNotFoundException(MemberErrorCode.NOT_FOUND_MEMBER));

        if (!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            throw new WrongPasswordException(MemberErrorCode.NOT_MATCHED_PASSWORD);
        }

        return new LoginResponse(member.getNickname(), member.getEmail());
    }

    public MemberEntity findById(long id){
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(MemberErrorCode.NOT_FOUND_MEMBER));
    }
}
