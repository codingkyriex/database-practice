package com.example.database_practice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.database_practice.config.ResponseResult;
import com.example.database_practice.dto.*;
import com.example.database_practice.pojo.Seller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: eric
 * @createTime: 2023-06-20 16:26
 **/

public interface SellerService extends IService<Seller> {
    ResponseResult<String> login(@RequestBody LoginDto dto);
    ResponseResult<String> register(@RequestBody LoginDto dto);
    ResponseResult<String> updateCode(@RequestBody LoginDto dto);
    ResponseResult<Object> getFullMsg(@RequestParam("id") Integer id);
    ResponseResult<String> updateSeller(@RequestBody UserDto dto);
    ResponseResult<String> createItem(@RequestBody ItemDto itemDto);
    ResponseResult<String> updateItem(@RequestBody ItemDto itemDto);
    ResponseResult<String> deleteItem(@RequestParam("id") Integer id);
    ResponseResult<String> addItemNum(@RequestBody StockDto stockDto);
    ResponseResult<Object> getFullItems(@RequestParam("id") Integer id);

}

