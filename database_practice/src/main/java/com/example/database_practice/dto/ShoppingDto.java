package com.example.database_practice.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/12 17:15
 */
@Data
public class ShoppingDto implements Serializable {
    Integer buyer;
    List<Integer> item;
    List<Integer> num;
}
