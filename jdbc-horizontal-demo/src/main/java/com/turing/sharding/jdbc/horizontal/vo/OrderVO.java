package com.turing.sharding.jdbc.horizontal.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderVO {

    private String orderNo;

    private BigDecimal amount;

}
