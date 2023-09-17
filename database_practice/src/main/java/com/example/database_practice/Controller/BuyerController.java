package com.example.database_practice.Controller;

import com.example.database_practice.config.ResponseResult;
import com.example.database_practice.dto.ChargeDto;
import com.example.database_practice.dto.FavoListDto;
import com.example.database_practice.dto.ShoppingDto;
import com.example.database_practice.service.BuyerService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/20 21:05
 */

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    BuyerService buyerService;

    @PostMapping("/buy")
    public ResponseResult<String> shopping(@RequestBody ShoppingDto dto){
        return buyerService.shopping(dto);
    }

    @PostMapping("/judge")
    public ResponseResult<String> judgeLackOfMoney(@RequestBody ShoppingDto dto){
        return buyerService.judgeLackOfMoney(dto);
    }

    @PostMapping("/recharge")
    public ResponseResult<String> addMoney(@RequestBody ChargeDto dto){
        return buyerService.addMoney(dto);
    }

    @GetMapping("/record")
    public ResponseResult<Object> getRecord(@RequestParam("id") Integer id){
        return buyerService.getPurchaseRecords(id);
    }

//    @PostMapping("/favo/add")
//    public ResponseResult<String> addFavoList(@RequestBody FavoListDto dto){
//        return buyerService.createFavoList(dto);
//    }

    @DeleteMapping("/favo/delete")
    public ResponseResult<String> deleteFavoList(@RequestParam("id") Integer id){
        return buyerService.deleteFavolist(id);
    }

    @GetMapping("/favo/get")
    public ResponseResult<Object> getFavoList(@RequestParam("id") Integer id){
        return buyerService.getMsgOfFavoList(id);
    }

    @PostMapping("/favo/update")
    public ResponseResult<String> updateFavoList(@RequestBody FavoListDto dto,@RequestParam("mode") Integer mode){
        return buyerService.updateFavoList(dto,mode);
    }

//    @GetMapping("/favo/judge")
//    public ResponseResult<String> judgeItemInFavolist(@RequestParam("item") Integer item, @RequestParam("buyer") Integer buyer){
//        return buyerService.judgeIsFavoed(item,buyer);
//    }

}
