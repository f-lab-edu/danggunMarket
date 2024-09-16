package com.example.danggunmarket.member.dto;

import com.example.danggunmarket.common.embedded.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {
    @Email(message = "이메일 형식으로 다시 작성해 주세요.")
    @Size(max = 255, message = "최대 길이가 255를 넘을 수 없습니다.")
    private String email;

    @Size(min=8, max=16, message = "비밀번호를 8자 이상 16자 이하로 입력해 주세요.")
    private String password;

    @Size(min = 2, max = 12, message = "닉네임은 2자 이상 12자 이하만 가능합니다.")
    private String nickname;

    @Size(min = 1, max = 10, message = "이름은 1자 이상 10자 이하만 입력해 주세요.")
    private String name;

    @NotBlank(message = "전화번호를 입력해 주세요")
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "형식이 잘못됐습니다.")
    private String telNumber;

    @Valid
    private Address homeAddress;


    @Builder
    public JoinRequest(String email, String password, String nickname, String name, String telNumber,
                       String city, String street, String detail, int zipCode) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.telNumber = telNumber;
        this.homeAddress = setAddress(city, street, detail, zipCode);
    }

    private Address setAddress(String city, String street, String detail, int zipCode){
        return Address.builder()
                .city(city)
                .street(street)
                .detail(detail)
                .zipCode(zipCode)
                .build();
    }

    public void setEncodePassword(String encodePassword){
        this.password = encodePassword;
    }
}
