package com.inventory.repository;

import com.inventory.entity.Item;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenRepository extends PagingAndSortingRepository<Item, Long> {

}
