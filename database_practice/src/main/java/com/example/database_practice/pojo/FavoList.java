package com.example.database_practice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/22 19:10
 */

@TableName(value ="favolist")
@Data
public class FavoList {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "buyer_id")
    private Integer buyerId;

    @TableField(value = "favo_items")
    private String favoItems;

    @TableField(value = "name")
    private String name;
}
