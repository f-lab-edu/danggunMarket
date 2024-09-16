package com.example.danggunmarket.common.embedded;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    private String city;
    private String street;
    private String detail;
    private int zipCode;
}
