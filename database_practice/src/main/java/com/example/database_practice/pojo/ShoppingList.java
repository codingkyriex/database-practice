package com.example.database_practice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.time.LocalDate;


/**
 * @description:
 * @author: eric
 * @time: 2023/6/20 16:18
 */
@TableName(value ="shoppinglist")
@Data
public class ShoppingList {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //买家id
    @TableField(value = "buyer_id")
    private Integer buyerId;

    //卖家id
    @TableField(value = "seller_id")
    private Integer sellerId;

    //商品id
    @TableField(value = "item_id")
    private Integer itemId;

    //购买个数
    @TableField(value = "num")
    private Integer num;

    @TableField(value = "date")
    private LocalDate date;


}
