package com.example.danggunmarket.member.mapper;

import com.example.danggunmarket.member.MemberEntity;
import com.example.danggunmarket.member.dto.JoinRequest;
import org.mapstruct.Mapper;

@Mapper
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberEntity toEntity(JoinRequest dto) {
        if (dto == null) {
            return null;
        }

        return MemberEntity.builder()
                .homeAddress(dto.getHomeAddress())
                .telNumber(dto.getTelNumber())
                .password(dto.getPassword())
                .nickname(dto.getNickname())
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }
}
