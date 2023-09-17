package com.example.database_practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.database_practice.pojo.Buyer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface BuyerMapper extends BaseMapper<Buyer> {

    @Select("select * from buyer where name = #{name}")
    Buyer selectByName(@Param("name") String name);
}
