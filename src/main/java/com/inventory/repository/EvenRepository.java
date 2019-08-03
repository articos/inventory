package com.inventory.repository;

import com.inventory.entity.Event;
import com.inventory.entity.Item;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenRepository extends PagingAndSortingRepository<Event, Long> {

    Event save(Event event);

}
