package com.example.database_practice.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/22 19:18
 */

@Data
public class FavoListDto implements Serializable {
    String name;
    Integer item;
    Integer buyerId;
}
