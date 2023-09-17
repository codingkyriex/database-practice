package com.example.database_practice.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/22 17:16
 */

/**
 * type: 0-价格，1-库存，2-种类
 * mode: 0-升序，1-降序
 */
@Data
public class SortDto implements Serializable {
    int sortType;
    int sortMode;
}
