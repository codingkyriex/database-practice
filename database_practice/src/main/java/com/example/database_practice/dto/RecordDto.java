package com.example.database_practice.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/26 22:35
 */

@Data
public class RecordDto {
    Integer id;
    LocalDate date;
    String item;
    String seller;
    int num;
    double money;
}
