package com.example.database_practice.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/12 16:49
 */

@Data
public class UserDto implements Serializable {
    Integer id;
    String nickName;
    String site;
    String headImg;
    String phone;
}
