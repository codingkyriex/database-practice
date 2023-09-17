package com.example.database_practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.database_practice.pojo.Buyer;
import com.example.database_practice.pojo.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @description:
 * @author: eric
 * @createTime: 2023-06-20 16:25
 **/

@Mapper
public interface SellerMapper extends BaseMapper<Seller> {
    @Select("select * from seller where name = #{name}")
    Seller selectByName(@Param("name") String name);
    @Select("select name from seller where id = #{id}")
    String selectNameById(@Param("id") Integer id);
}
