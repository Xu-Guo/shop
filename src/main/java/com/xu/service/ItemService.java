package com.xu.service;

import com.xu.entity.Item;

import java.util.List;

public interface ItemService {

    public void save(Item item);

    public List<Item> findAll();

    public void deleteById(Integer id);

    public Item findById(Integer id);
}
