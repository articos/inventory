package com.inventory.service;

import com.inventory.classifier.ItemState;
import com.inventory.entity.Event;
import com.inventory.entity.Item;
import com.inventory.model.EventDTO;
import com.inventory.model.ItemDTO;
import com.inventory.repository.EvenRepository;
import com.inventory.repository.ItemRepository;
import com.inventory.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    //TODO refactor
    @Transactional
    public void createEvent(EventDTO eventDTO, ItemDTO itemDTO) {
        Optional<Item> itemOptional = itemRepository.findById(itemDTO.getItemId());
        if (itemOptional.isPresent()) {
            Item itemForUpdateState = itemOptional.get();
            Event eventToSave = mapper.eventDTOToEvent(eventDTO);
            setItemStateBasedOnEvent(eventDTO, itemForUpdateState);
            eventToSave.setItem(itemForUpdateState);
            evenRepository.save(eventToSave);
        }
    }

    private void setItemStateBasedOnEvent(EventDTO eventDTO, Item item) {
        switch (eventDTO.getEventType()) {
            case LENT:
                item.setState("LENT");
                break;
            case NORMAL:
                item.setState("NORMAL");
                break;
            case SERVICE:
                item.setState("SERVICE");
                break;
            case DECOMMISSION:
                item.setState("DECOMMISSIONED");
                break;
            default:
                item.setState("UNDEFINED");
        }
    }


}
