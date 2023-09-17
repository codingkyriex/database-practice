package com.example.database_practice.Controller;

import com.example.database_practice.config.ResponseResult;
import com.example.database_practice.dto.PageDto;
import com.example.database_practice.dto.SortDto;
import com.example.database_practice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/21 20:37
 */

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/page")
    public ResponseResult<Object> getPagedItems(@RequestParam("no") int no,@RequestParam("size") int size, @RequestParam(value = "type",required = false) Integer type,@RequestParam(value = "col",required = false) Integer col,@RequestParam(value = "mode",required = false) Integer mode){
        return itemService.getPagedItem(no, size ,type ,col,mode);
    }

    @GetMapping("/view")
    public ResponseResult<Object> getItemById(@RequestParam("id") Integer id){
        return itemService.getItemById(id);
    }

    @GetMapping("/search")
    public ResponseResult<Object> searchByKey(@RequestParam("key") String key,@RequestParam("num") int num){
        return itemService.getItemByFuzzyQuery(key,num);
    }
}
