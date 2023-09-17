package com.example.database_practice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/20 16:11
 */
@TableName(value ="seller")
@Data
public class Seller implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 昵称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 卖家电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 发货地址
     */
    @TableField(value = "site")
    private String site;

    /**
     * 头像
     */
    @TableField(value = "head_img")
    private String headImg;


    @TableField(value = "earn")
    private double earn;

}
