package com.example.database_practice.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/12 16:18
 */
@Data
public class ItemDto implements Serializable {
    Integer id;
    Integer sellerId;
    int type;
    String name;
    int num;
    String img;
    double value;
    String detailMsg;
}
