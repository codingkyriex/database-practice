package com.example.database_practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.database_practice.config.AppHttpCodeEnum;
import com.example.database_practice.config.ResponseResult;
import com.example.database_practice.dto.*;
import com.example.database_practice.mapper.BuyerMapper;
import com.example.database_practice.mapper.ItemMapper;
import com.example.database_practice.mapper.SellerMapper;
import com.example.database_practice.mapper.ShoppingListMapper;
import com.example.database_practice.pojo.Buyer;
import com.example.database_practice.pojo.Item;
import com.example.database_practice.pojo.Seller;
import com.example.database_practice.service.ItemService;
import com.example.database_practice.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: eric
 * @time: 2023/6/20 17:18
 */

@Service
public class SellerServiceImpl extends ServiceImpl<SellerMapper, Seller> implements SellerService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private ShoppingListMapper shoppingListMapper;

    // 登录，如果查找不到就新建用户，之后返回id
    @Override
    public ResponseResult login(LoginDto dto) {
        Seller seller = sellerMapper.selectByName(dto.getName());
        if(seller ==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_USER);
        }
        Integer id = seller.getId();
        //密码正确，返回id
        if(dto.getCode().equals(seller.getPassword())){
            return ResponseResult.okResult(200, seller.getId());
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }
    }

    @Override
    public ResponseResult<String> register(LoginDto dto) {
        if(sellerMapper.selectByName(dto.getName())!=null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DUPLICATE_USER_NAME);
        }
        Seller seller = new Seller();
        seller.setName(dto.getName());
        seller.setPassword(dto.getCode());
        save(seller);
        return ResponseResult.okResult(seller.getId());
    }

    @Override
    public ResponseResult<String> updateCode(LoginDto dto) {
        Seller seller = sellerMapper.selectByName(dto.getName());
        if(seller == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_USER);
        }
        seller.setPassword(dto.getCode());
        updateById(seller);
        return ResponseResult.okResult(null);
    }

    //查看具体信息
    @Override
    public ResponseResult<Object> getFullMsg(Integer id) {
        Seller seller = getById(id);
        if(seller != null){
            return ResponseResult.okResult(seller);
        }else{
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_USER);
        }
    }

    //修改用户信息
    @Override
    public ResponseResult<String> updateSeller(UserDto dto) {
        Integer id = dto.getId();
        Seller seller = getById(id);
        if(seller == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_USER);
        }
        seller.setName(dto.getNickName());
        seller.setSite(dto.getSite());
        seller.setHeadImg(dto.getHeadImg());
        seller.setPhone(dto.getPhone());
        updateById(seller);
        return ResponseResult.okResult(null);
    }

    @Override
    public ResponseResult<String> createItem(ItemDto itemDto) {
            Item item = new Item();
            item.setType(itemDto.getType());
            item.setName(itemDto.getName());
            item.setValue(itemDto.getValue());
            item.setNum(itemDto.getNum());
            item.setImage(itemDto.getImg());
            item.setDetailMsg(itemDto.getDetailMsg());
            item.setSellerId(itemDto.getSellerId());
            System.out.println("id"+itemDto.getSellerId());
            if(getById(itemDto.getSellerId()) == null){
                return ResponseResult.errorResult(AppHttpCodeEnum.MISS_USER);
            }
            int res = itemMapper.insert(item);
            if (res == 0){
                return ResponseResult.errorResult(AppHttpCodeEnum.INSERT_ERROR);
            }
            Integer id = item.getId();
            return ResponseResult.okResult(id);
    }

    @Override
    public ResponseResult<String> updateItem(ItemDto itemDto) {
            Integer id = itemDto.getId();
            Item item=itemMapper.selectById(id);
            item.setType(itemDto.getType());
            item.setName(itemDto.getName());
            item.setValue(itemDto.getValue());
            item.setDetailMsg(itemDto.getDetailMsg());
            item.setImage(itemDto.getImg());
            int res=itemMapper.updateById(item);
            if(res<0)
                ResponseResult.errorResult(AppHttpCodeEnum.UPDATE_ERROR);
            return ResponseResult.okResult(null);
    }

    @Override
    public ResponseResult<String> deleteItem(Integer id) {
        Item item = itemMapper.selectById(id);
        if(item == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_ITEM);
        }
        shoppingListMapper.deleteListByItem(id);
        itemMapper.deleteById(id);
        return ResponseResult.okResult(null);
    }

    @Override
    public ResponseResult<String> addItemNum(StockDto stockDto) {
        Item item = itemMapper.selectById(stockDto.getId());
        if(item == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_ITEM);
        }
        item.setNum(item.getNum() + stockDto.getNum());
        itemMapper.updateById(item);
        return ResponseResult.okResult(null);
    }

    @Override
    public ResponseResult<Object> getFullItems(Integer id) {
        Seller seller = getById(id);
        if(seller == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_USER);
        }
        List<Item> items = itemMapper.selectItemsBySellerId(id);
        return ResponseResult.okResult(items);
    }
}
