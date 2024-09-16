package com.example.danggunmarket.member;

import com.example.danggunmarket.member.dto.JoinRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testSignup() throws Exception {
        JoinRequest joinRequest = JoinRequest.builder()
                .email("ee22e@aaa.com")
                .name("asd")
                .nickname("aefaef22")
                .password("aggeaegeg44")
                .telNumber("010-2222-3333")
                .detail("104동 502호")
                .city("asd")
                .zipCode(123)
                .street("경인로 134번길 72")
                .build();

        String json = mapper.writeValueAsString(joinRequest);
        mvc.perform(post("/v1/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void testDuplicatedEmail() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/v1/check/email")
                        .param("email", "ee22e@aaa.com"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}