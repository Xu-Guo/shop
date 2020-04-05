package com.xu.service;

import com.xu.entity.ShopInfo;

import java.util.List;

public interface ShopInfoService {
    public void save(ShopInfo shopInfo);

    public List<ShopInfo> findAll();

    public void deleteById(Integer id);

    public ShopInfo findById(Integer id);
}
