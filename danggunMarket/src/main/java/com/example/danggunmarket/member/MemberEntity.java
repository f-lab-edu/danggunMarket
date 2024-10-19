package com.example.danggunmarket.member;

import com.example.danggunmarket.common.embedded.Address;
import com.example.danggunmarket.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;
    private String name;

    @Column(unique = true)
    private String nickname;

    @Column(unique = true)
    private String email;
    private String password;
    private String telNumber;

    @Enumerated(EnumType.STRING)
    private MemberRole role;
    @Embedded
    private Address homeAddress;

    @Builder
    public MemberEntity(String name, String nickname,
                        String email, String password, String telNumber,
                        Address homeAddress) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.telNumber = telNumber;
        this.homeAddress = homeAddress;
        this.role = MemberRole.ROLE_MEMBER;
    }
}
