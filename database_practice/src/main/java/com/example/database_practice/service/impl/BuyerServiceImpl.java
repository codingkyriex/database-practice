package com.example.database_practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.database_practice.config.AppHttpCodeEnum;
import com.example.database_practice.config.ResponseResult;
import com.example.database_practice.dto.*;
import com.example.database_practice.mapper.*;
import com.example.database_practice.pojo.*;
import com.example.database_practice.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: eric
 * @time: 2023/5/7 13:59
 */
@Service

public class BuyerServiceImpl extends ServiceImpl<BuyerMapper, Buyer> implements BuyerService {

    @Autowired
    BuyerMapper buyerMapper;

    @Autowired
    ShoppingListMapper shoppingListMapper;

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    SellerMapper sellerMapper;

    @Autowired
    FavoListMapper favoListMapper;

    // 登录，如果查找不到就新建用户，之后返回id
    @Override
    public ResponseResult login(LoginDto dto) {
        Buyer buyer = buyerMapper.selectByName(dto.getName());
        if(buyer == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_USER);
        }
        System.out.println("id = "+buyer.getId());
        //密码正确，返回id
        if(dto.getCode().equals(buyer.getPassword())){
            System.out.println("id = "+buyer.getId());
            return ResponseResult.okResult(200, buyer.getId());
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }
    }

    @Override
    public ResponseResult<String> register(LoginDto dto) {
        if(buyerMapper.selectByName(dto.getName())!=null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DUPLICATE_USER_NAME);
        }
        Buyer buyer = new Buyer();
        buyer.setName(dto.getName());
        buyer.setPassword(dto.getCode());
        save(buyer);
        return ResponseResult.okResult(buyer.getId());
    }

    @Override
    public ResponseResult<String> updateCode(LoginDto dto) {
        Buyer buyer=buyerMapper.selectByName(dto.getName());
        if(buyer == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_USER);
        }
        buyer.setPassword(dto.getCode());
        updateById(buyer);
        return ResponseResult.okResult(null);
    }


    //查看具体信息
    @Override
    public ResponseResult<Object> getFullMsg(Integer id) {
        Buyer buyer = getById(id);
        if(buyer != null){
            return ResponseResult.okResult(buyer);
        }else{
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_USER);
        }
    }

    //修改用户信息
    @Override
    public ResponseResult<String> updateBuyer(UserDto dto) {
        Integer id = dto.getId();
        Buyer buyer = getById(id);
        if(buyer == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_USER);
        }
        buyer.setSite(dto.getSite());
        buyer.setHeadImg(dto.getHeadImg());
        buyer.setName(dto.getNickName());
        buyer.setPhone(dto.getPhone());
        updateById(buyer);
        return ResponseResult.okResult(null);
    }

    // 购物
    @Override
    public ResponseResult<String> shopping(ShoppingDto dto) {
        List<Integer> nums = dto.getNum();
        List<Integer> items = dto.getItem();
        if(items.size() > itemMapper.getTotalNum()){
            return ResponseResult.errorResult(AppHttpCodeEnum.LACK_OF_ITEMS);
        }
        for(int i=0;i<nums.size();i++){
            int isOk = buySingleOne(dto.getBuyer(),items.get(i),nums.get(i));
            if(isOk == 0){
                return ResponseResult.errorResult(AppHttpCodeEnum.MISS_ITEM);
            }else if(isOk == 1){
                return ResponseResult.errorResult(AppHttpCodeEnum.LACK_OF_MONEY);
            }
        }
        return ResponseResult.okResult(null);
    }

    private int buySingleOne(Integer buyerId, Integer itemId , int num) {
        Buyer buyer = getById(buyerId);
        Integer sellerId= itemMapper.selectSellerById(itemId);
        Seller seller = sellerMapper.selectById(sellerId);

        Item item = itemMapper.selectById(itemId);
        if(item == null){
            return 0;
        }
        double money = item.getValue() * num;
        if(money > buyer.getMoney()){
            return 1;
        }
        //钱够
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setBuyerId(buyerId);
        shoppingList.setItemId(itemId);
        shoppingList.setNum(num);
        shoppingList.setSellerId(sellerId);
        shoppingList.setDate(LocalDate.now());
        shoppingListMapper.insert(shoppingList);
        buyer.setMoney(buyer.getMoney()-money);
        seller.setEarn(seller.getEarn()+money);
        updateById(buyer);
        sellerMapper.updateById(seller);
        item.setNum(item.getNum()-num);
        itemMapper.updateById(item);
        return 2;
    }


    @Override
    public ResponseResult<String> addMoney(ChargeDto dto) {
        Buyer buyer = getById(dto.getId());
        if(buyer == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_USER);
        }
        buyer.setMoney(buyer.getMoney() + dto.getMoney());
        updateById(buyer);
        return ResponseResult.okResult(null);
    }

    @Override
    public ResponseResult<Object> getPurchaseRecords(Integer id) {
        Buyer buyer = getById(id);
        if(buyer == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_USER);
        }
        List<ShoppingList> shoppingLists = shoppingListMapper.selectRecordsByBuyerId(id);
        shoppingLists.sort(Comparator.comparing(ShoppingList::getDate));
        List<HashMap<String,String>> records = new ArrayList<>();
        for (ShoppingList shoppingList : shoppingLists) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", shoppingList.getId().toString());
            map.put("item", itemMapper.selectNameById(shoppingList.getItemId()));
            map.put("date", shoppingList.getDate().toString());
            map.put("seller", sellerMapper.selectNameById(shoppingList.getSellerId()));
            map.put("num", shoppingList.getNum().toString());
            map.put("money", String.valueOf((shoppingList.getNum() * itemMapper.selectValueById(shoppingList.getItemId()))));
            records.add(map);
        }
        return ResponseResult.okResult(records);
    }

    @Override
    public ResponseResult<String> judgeLackOfMoney(ShoppingDto dto) {
        List<Integer> nums = dto.getNum();
        List<Integer> items = dto.getItem();
        double sum=0.0;
        for(int i=0;i<nums.size();i++){
            Item item = itemMapper.selectById(items.get(i));
            sum += item.getValue() * nums.get(i);
            }
        Buyer buyer = getById(dto.getBuyer());
        if(buyer == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_USER);
        }
        if(buyer.getMoney() < sum){
            return ResponseResult.okResult(-1);
        }
        return ResponseResult.okResult(1);
    }

    @Override
    public ResponseResult<String> createFavoList(FavoListDto dto) {
        Buyer buyer = getById(dto.getBuyerId());
        if(buyer == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_USER);
        }
        FavoList favoList = new FavoList();
        favoList.setBuyerId(dto.getBuyerId());
        favoList.setName(dto.getName());
        favoList.setFavoItems("");
        favoListMapper.insert(favoList);
        return ResponseResult.okResult(favoList.getId());
    }

    @Override
    public ResponseResult<Object> getMsgOfFavoList(Integer id) {
        FavoList favoList = favoListMapper.selectFavoListByBuyerId(id);
        if(favoList == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_FAVOLIST);
        }
        String favoItems = favoList.getFavoItems();
        String[] s = favoItems.split(" ");
        List<HashMap<String,String>> favos = new ArrayList<>();
        for(int i=0;i<s.length;i++){
            HashMap<String, String> map = new HashMap<>();
            map.put("id", String.valueOf((i+1)));
            map.put("name",favoList.getName());
            map.put("item_id",s[i]);
            map.put("item",itemMapper.selectNameById(Integer.parseInt(s[i])));
            favos.add(map);
        }
        return ResponseResult.okResult(favos);
    }

    @Override
    public ResponseResult<String> updateFavoList(FavoListDto dto,Integer mode) {
        Buyer buyer = getById(dto.getBuyerId());
        if(buyer == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_USER);
        }
        FavoList favoList = favoListMapper.selectFavoListByBuyerId(dto.getBuyerId());
        FavoList favo = new FavoList();
        if(favoList == null){
            favo.setBuyerId(dto.getBuyerId());
            favo.setName(dto.getName());
            favo.setFavoItems("");
            favoListMapper.insert(favo);
            favoList = favo;
        }
        // 新增单个收藏
        if(mode == 0){
            //已经存在收藏
            if(judgeIsFavoed(dto.getItem(),dto.getBuyerId()))
                return ResponseResult.errorResult(AppHttpCodeEnum.IS_FAVORITED);
            else{
                String favoItems = favoList.getFavoItems();
                if(Objects.equals(favoItems, ""))
                    favoItems += dto.getItem().toString();
                else
                    favoItems += " "+dto.getItem().toString();
                favoList.setFavoItems(favoItems);
            }
        // 取消单个收藏
        } else if (mode == 1) {
            String favoItems = favoList.getFavoItems();
            ArrayList<String> strings = new ArrayList<>(Arrays.asList(favoItems.split(" ")));
            strings.remove(dto.getItem().toString());
            String newFavoItems = String.join(" ", strings);
            favoList.setFavoItems(newFavoItems);
        } else{
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if(dto.getName() != null)
            favoList.setName(dto.getName());
        favoListMapper.updateById(favoList);
        return ResponseResult.okResult(null);
    }

    @Override
    public ResponseResult<String> deleteFavolist(Integer id) {
        FavoList favoList = favoListMapper.selectFavoListByBuyerId(id);
        if(favoList == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.MISS_ITEM);
        }
        favoListMapper.deleteById(id);
        return ResponseResult.okResult(null);
    }


    private boolean judgeIsFavoed(Integer item, Integer buyer) {
        FavoList favoList = favoListMapper.selectFavoListByBuyerId(buyer);
        String favoItems = favoList.getFavoItems();
        String[] s = favoItems.split(" ");
        for (String value : s) {
            if (!value.isEmpty() && Integer.parseInt(value) == item) {
                return true;
            }
        }
        return false;
    }


}
