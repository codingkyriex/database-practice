package com.example.database_practice.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/21 20:04
 */

@Data
public class ChargeDto implements Serializable {
    Integer id;
    double money;
}
