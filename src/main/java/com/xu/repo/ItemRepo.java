package com.xu.repo;

import com.xu.entity.Item;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepo extends CrudRepository<Item, Integer> {

}
