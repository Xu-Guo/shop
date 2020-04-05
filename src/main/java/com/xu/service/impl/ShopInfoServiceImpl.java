package com.xu.service.impl;

import com.xu.entity.Item;
import com.xu.entity.ShopInfo;
import com.xu.repo.ItemRepo;
import com.xu.repo.ShopInfoRepo;
import com.xu.service.ShopInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service("ShopInfoService")
public class ShopInfoServiceImpl implements ShopInfoService {
    @Resource
    private ShopInfoRepo shopInfoRepo;

    @Override
    public void save(ShopInfo shopInfo) {
        shopInfoRepo.save(shopInfo);
    }

    @Override
    public List<ShopInfo> findAll() {
        return (List<ShopInfo>)shopInfoRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        shopInfoRepo.deleteById(id);
    }

    @Override
    public ShopInfo findById(Integer id) {
        Optional<ShopInfo> shopInfoOpt = shopInfoRepo.findById(id);
        if(shopInfoOpt.isPresent()){
            return shopInfoOpt.get();
        } else {
            return null;
        }
    }
}
