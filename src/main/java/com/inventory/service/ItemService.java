package com.inventory.service;

import com.inventory.entity.Item;
import com.inventory.model.ItemDTO;
import com.inventory.repository.ItemRepository;
import com.inventory.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
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
        return mapper.itemsToItemsDTOs(items);
    }

    public Item findById(Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        return itemOptional.get();
    }

    public void createItem(ItemDTO itemDTO) throws IOException {
        Date date = new Date();
        itemDTO.setRegisterDate(date);
        Item item = mapper.itemDTOToItem(itemDTO);
        item.setImage(itemDTO.getImage().getBytes());
        itemRepository.save(item);
    }
}
