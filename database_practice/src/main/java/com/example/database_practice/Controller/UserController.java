package com.example.database_practice.Controller;

import com.example.database_practice.config.AppHttpCodeEnum;
import com.example.database_practice.config.ResponseResult;
import com.example.database_practice.dto.LoginDto;
import com.example.database_practice.dto.UserDto;
import com.example.database_practice.service.BuyerService;
import com.example.database_practice.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/12 13:07
 */

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    BuyerService buyerService;
    @Autowired
    SellerService sellerService;

    @PostMapping("/login")
    public ResponseResult<String> login(@RequestBody LoginDto dto,@RequestParam("mode") int mode){
        if(mode == 0)
            return buyerService.login(dto);
        if(mode == 1)
            return sellerService.login(dto);
        else return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
    }

    @PostMapping("/msg/change")
    public ResponseResult<String> changeMsg(@RequestBody UserDto dto,@RequestParam("mode") int mode){
        if(mode == 0)
            return buyerService.updateBuyer(dto);
        else
            return sellerService.updateSeller(dto);
    }

    @GetMapping("/msg/get")
    public ResponseResult<Object> getMsg(@RequestParam("id") Integer id ,@RequestParam("mode") int mode){
        if(mode==0){
            return buyerService.getFullMsg(id);
        }
        else
            return sellerService.getFullMsg(id);
    }

    @PostMapping("/code")
    public ResponseResult<String> changeCode(@RequestBody LoginDto dto,@RequestParam("mode") int mode){
        if(mode == 0){
            return buyerService.updateCode(dto);
        }
        else
            return sellerService.updateCode(dto);
    }

    @PostMapping("/register")
    public ResponseResult<String> register(@RequestBody LoginDto dto,@RequestParam("mode") int mode){
        if(mode == 0){
            return buyerService.register(dto);
        }
        else
            return sellerService.register(dto);
    }

}
