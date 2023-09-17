package com.example.database_practice.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/20 18:17
 */

@Data
public class StockDto implements Serializable {
    Integer id;
    int num;
}
