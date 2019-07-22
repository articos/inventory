package com.inventory.service;

import com.inventory.entity.Item;
import com.inventory.model.ItemDTO;
import com.inventory.repository.ItemRepository;
import com.inventory.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper mapper;

    public List<ItemDTO> findAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemDTO> itemDTOs = mapper.itemsToItemsDTOs(items);
        return itemDTOs;
    }

    public Item findById(Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        Item item = itemOptional.get();
        return item;
    }

    public void createItem(ItemDTO itemDTO) {
        Date date = new Date();
        itemDTO.setRegisterDate(date);
        Item item = mapper.itemDTOToItem(itemDTO);
//        Item savedItem = itemRepository.save(item);
        itemRepository.save(item);
    }
}
