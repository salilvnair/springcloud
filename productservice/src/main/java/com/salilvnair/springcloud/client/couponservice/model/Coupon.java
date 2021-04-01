package com.salilvnair.springcloud.client.couponservice.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    private Long id;
    private String code;
    private BigDecimal discount;
    private String expDate;
}
