package com.example.database_practice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description:
 * @author: eric
 * @time: 2023/5/6 16:50
 */

@TableName(value ="item")
@Data
public class Item {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "type")
    private int type;

    @TableField(value = "name")
    private String name;

    @TableField(value = "value")
    private double value;

    @TableField(value = "detail_msg")
    private String detailMsg;

    @TableField(value = "image")
    private String image;

    @TableField(value = "num")
    private int num;

    @TableField(value = "sellerId")
    private Integer sellerId;

    @TableField(value = "view_num")
    private int view_num;

}
