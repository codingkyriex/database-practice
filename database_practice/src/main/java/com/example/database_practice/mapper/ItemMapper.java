package com.example.database_practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.database_practice.pojo.Buyer;
import com.example.database_practice.pojo.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/12 16:12
 */

@Mapper
public interface ItemMapper extends BaseMapper<Item> {

    @Select("select sellerId from item where id = #{id}")
    Integer selectSellerById(@Param("id") Integer id);

    @Select("select * from item where name like CONCAT('%', #{key}, '%')")
    List<Item> selectItemByFuzzyKey(@Param("key") String key);

    @Select("select * from item order by view_num desc limit #{num}")
    List<Item> selectDefaultItem(@Param("num") int num);

    @Select("select * from item where sellerId = #{id}")
    List<Item> selectItemsBySellerId(@Param("id") Integer id);

    @Select({
            "<script>",
            "SELECT * FROM item",
            "<when test='type != null'>",
            "  WHERE type = ${type}",
            "</when>",
            "<when test='col != null and mode != null'>",
            "   ORDER BY ${col} ${mode}",
            "</when>",
            "  LIMIT ${no} , ${size}",
            "</script>"
    })
    List<Item> selectItemsByOrder(@Param("no") String no,@Param("size") String size,@Param("type") String type,@Param("col") String col, @Param("mode") String mode);

    @Select("select name from item where id = #{id}")
    String selectNameById(@Param("id") Integer id);

    @Select("select value from item where id = #{id}")
    Double selectValueById(@Param("id") Integer id);

    @Select("select count(*) from item")
    int getTotalNum();
}
