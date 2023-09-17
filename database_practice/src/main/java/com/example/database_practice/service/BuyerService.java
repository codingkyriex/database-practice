package com.example.database_practice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.database_practice.config.ResponseResult;
import com.example.database_practice.dto.*;
import com.example.database_practice.pojo.Buyer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: eric
 * @createTime: 2023-05-07 13:51
 **/

public interface BuyerService extends IService<Buyer> {
    ResponseResult<String> login(@RequestBody LoginDto dto);
    ResponseResult<String> register(@RequestBody LoginDto dto);
    ResponseResult<String> updateCode(@RequestBody LoginDto dto);
    ResponseResult<Object> getFullMsg(Integer id);
    ResponseResult<String> updateBuyer(@RequestBody UserDto dto);
    ResponseResult<String> shopping(@RequestBody ShoppingDto dto);
    ResponseResult<String> addMoney(@RequestBody ChargeDto dto);
    ResponseResult<Object> getPurchaseRecords(@RequestParam("id") Integer id);
    ResponseResult<String> judgeLackOfMoney(@RequestBody ShoppingDto dto);
    ResponseResult<String> createFavoList(@RequestBody FavoListDto dto);
    ResponseResult<Object> getMsgOfFavoList(@RequestParam("id") Integer id);
    ResponseResult<String> updateFavoList(@RequestBody FavoListDto dto,@RequestParam("mode") Integer mode);
    ResponseResult<String> deleteFavolist(@RequestParam("id") Integer id);
//    ResponseResult<String> judgeIsFavoed(@RequestParam("item") Integer item,@RequestParam("buyer") Integer buyer);
}
