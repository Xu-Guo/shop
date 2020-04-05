package com.xu.service.impl;

import com.xu.entity.Item;
import com.xu.repo.ItemRepo;
import com.xu.service.ItemService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service("ItemService")
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemRepo itemRepo;

    @Override
    public void save(Item item) {
        itemRepo.save(item);
    }

    @Override
    public List<Item> findAll() {
        return (List<Item>)itemRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        itemRepo.deleteById(id);
    }

    @Override
    public Item findById(Integer id) {
        Optional<Item> itemOpt = itemRepo.findById(id);
        if(itemOpt.isPresent()){
            return itemOpt.get();
        } else {
            return null;
        }
    }


}
