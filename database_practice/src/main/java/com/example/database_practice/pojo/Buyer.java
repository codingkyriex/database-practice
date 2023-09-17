package com.example.database_practice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.io.Serializable;

/**
 * @description: 用户
 * @author: eric
 * @time: 2023/5/6 16:50
 */
@TableName(value ="buyer")
@Data
public class Buyer implements Serializable {
    /**
     *
     */
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
     * 用户电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 收货地址
     */
    @TableField(value = "site")
    private String site;


    /**
     * 头像
     */
    @TableField(value = "head_img")
    private String headImg;


    @TableField(value = "money")
    private double money;



}
