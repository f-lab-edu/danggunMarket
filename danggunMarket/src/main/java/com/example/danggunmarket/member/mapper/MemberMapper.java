package com.example.danggunmarket.member.mapper;

import com.example.danggunmarket.member.MemberEntity;
import com.example.danggunmarket.member.dto.JoinRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberEntity toEntity(JoinRequest dto);

}
