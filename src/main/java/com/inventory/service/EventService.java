package com.inventory.service;

import com.inventory.entity.Event;
import com.inventory.entity.Item;
import com.inventory.model.EventDTO;
import com.inventory.model.ItemDTO;
import com.inventory.repository.EvenRepository;
import com.inventory.repository.ItemRepository;
import com.inventory.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService {

    @Autowired
    private EvenRepository evenRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper mapper;

    public void createEvent(EventDTO eventDTO, ItemDTO itemDTO) {
        Optional<Item> itemOptional = itemRepository.findById(itemDTO.getItemId());
        if (itemOptional.isPresent()) {
            Item item1= itemOptional.get();
            Event eventToSave =  mapper.eventDTOToEvent(eventDTO);
            eventToSave.setItem(item1);
            evenRepository.save(eventToSave);
        }
    }


}
