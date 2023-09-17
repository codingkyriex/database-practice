package com.example.database_practice.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/21 20:26
 */


@Data
public class PageDto implements Serializable {
    int pageNo;
    int pageSize;
}
