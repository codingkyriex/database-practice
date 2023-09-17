package com.example.database_practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.database_practice.config.AppHttpCodeEnum;
import com.example.database_practice.config.ResponseResult;
import com.example.database_practice.dto.ItemDto;
import com.example.database_practice.dto.PageDto;
import com.example.database_practice.dto.SortDto;
import com.example.database_practice.mapper.ItemMapper;
import com.example.database_practice.pojo.Item;
import com.example.database_practice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/12 16:26
 */

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired
    ItemMapper itemMapper;

    @Override
    public ResponseResult<Object> getPagedItem(int no,int size,Integer type,Integer col,Integer mode) {
        String coll = null;
        String modee = null;
        String typee = null;
        if(col !=null&&mode !=null){
            if(col == 0){
                coll = "value";
            }else {
                coll ="num";
            }
            if(mode == 0){
                modee = "asc";
            }else {
                modee ="desc";
            }
        }
        if(type !=null){
            typee = String.valueOf(type);
        }
        List<Item> items = itemMapper.selectItemsByOrder(String.valueOf(no), String.valueOf(size), typee, coll, modee);

        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        long total;
        if(type !=null){
            queryWrapper.eq("type", type);
            // 获取指定type的元素个数
            total = itemMapper.selectCount(queryWrapper);
        }else
            total = itemMapper.selectCount(null);
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("currentPage", no);
        result.put("totalPages", total/ size+1);
        result.put("data", items);

        return ResponseResult.okResult(result);
    }

    @Override
    public ResponseResult<Object> getItemByFuzzyQuery(String key,int num) {
        List<Item> item = itemMapper.selectItemByFuzzyKey(key);
        if(num < itemMapper.getTotalNum()){
            item = item.subList(0,num);
        }
        if(item == null){
            return getDefaultItems(10);
        }
        return ResponseResult.okResult(item);
    }

    @Override
    public ResponseResult<Object> getDefaultItems(int num) {
        List<Item> items = itemMapper.selectDefaultItem(num);
        return ResponseResult.okResult(items);
    }

    @Override
    public ResponseResult<Object> getItemById(Integer id) {
        Item item = getById(id);
        item.setView_num(item.getView_num() + 1);
        updateById(item);
        return ResponseResult.okResult(item);
    }

//    @Override
//    public ResponseResult<Object> getSortedItem(int type,int mode) {
//        String col = "";
//        String m;
//        switch (type) {
//            case 0 : {col = "value";break;}
//            case 1 : {col = "num";break;}
//            case 2 : {col = "type";break;}
//        }
//        if(mode == 0){
//            m = "asc";
//        }else m = "desc";
//        List<Item> items = itemMapper.selectItemsByOrder(col, m);
//        return ResponseResult.okResult(items);
//    }


}
