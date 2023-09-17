package com.example.database_practice.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: eric
 * @time: 2023/5/7 12:42
 */

@Data
public class LoginDto implements Serializable {
    String code;
    String name;
}
