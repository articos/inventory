package com.inventory.repository;

import com.inventory.entity.Item;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

    List<Item> findAll();

    Optional<Item> findById(Integer id);

    Item save(Item item);

}
