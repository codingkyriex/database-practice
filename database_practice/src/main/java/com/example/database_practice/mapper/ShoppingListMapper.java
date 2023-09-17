package com.example.database_practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.database_practice.pojo.ShoppingList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:
 * @author: eric
 * @createTime: 2023-06-20 16:26
 **/

@Mapper
public interface ShoppingListMapper extends BaseMapper<ShoppingList> {

    @Select("select * from shoppinglist where buyer_id = #{id}")
    List<ShoppingList> selectRecordsByBuyerId(@Param("id") Integer id);

    @Delete("delete from shoppinglist where item_id = #{id}")
    void deleteListByItem(@Param("id") Integer id);


}
