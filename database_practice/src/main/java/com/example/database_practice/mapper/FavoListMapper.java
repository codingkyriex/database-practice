package com.example.database_practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.database_practice.pojo.FavoList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @description:
 * @author: eric
 * @createTime: 2023-06-22 19:14
 **/

@Mapper
public interface FavoListMapper extends BaseMapper<FavoList> {

    @Select("select * from favolist where buyer_id = #{id}")
    FavoList selectFavoListByBuyerId(@Param("id") Integer id);


}
