package com.example.database_practice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.database_practice.config.ResponseResult;
import com.example.database_practice.dto.ItemDto;
import com.example.database_practice.dto.PageDto;
import com.example.database_practice.dto.SortDto;
import com.example.database_practice.pojo.Item;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: eric
 * @createTime: 2023-06-12 16:16
 **/

public interface ItemService extends IService<Item> {
    ResponseResult<Object> getPagedItem(@RequestParam("no") int no,@RequestParam("size") int size,@RequestParam("type") Integer type,@RequestParam("col")Integer col,@RequestParam("mode")Integer mode);
    ResponseResult<Object> getItemByFuzzyQuery(@RequestParam("key") String key,@RequestParam("num") int num);
    ResponseResult<Object> getDefaultItems(@RequestParam("num") int num);
    ResponseResult<Object> getItemById(@RequestParam("id") Integer id);
//    ResponseResult<Object> getSortedItem(@RequestParam("type")int type,@RequestParam("mode")int mode);

}
