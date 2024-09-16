package com.example.danggunmarket.common.embedded;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    @NotBlank(message = "도시를 입력해 주세요.")
    private String city;
    @NotBlank(message = "도로명을 입력해 주세요")
    private String street;
    @NotBlank(message = "상세 주소를 입력해 주세요")
    private String detail;

    @Positive(message = "숫자로 입력해 주세요")
    private int zipCode;
}