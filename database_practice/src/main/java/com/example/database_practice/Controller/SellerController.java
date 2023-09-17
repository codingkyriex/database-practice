package com.example.database_practice.Controller;

import com.example.database_practice.config.ResponseResult;
import com.example.database_practice.dto.ItemDto;
import com.example.database_practice.dto.StockDto;
import com.example.database_practice.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/20 20:59
 */

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/create")
    public ResponseResult<String> createItem(@RequestBody ItemDto dto){
        return sellerService.createItem(dto);
    }

    @PostMapping("/update")
    public ResponseResult<String> updateItem(@RequestBody ItemDto dto){
        return sellerService.updateItem(dto);
    }

    @PostMapping("/add")
    public ResponseResult<String> addItem(@RequestBody StockDto dto){
        return sellerService.addItemNum(dto);
    }

    @GetMapping("/items")
    public ResponseResult<Object> getItems(@RequestParam("id") Integer id){
        return sellerService.getFullItems(id);
    }

    @DeleteMapping("/delete")
    public ResponseResult<String> deleteItem(@RequestParam("id") Integer id){
        return sellerService.deleteItem(id);
    }
}
