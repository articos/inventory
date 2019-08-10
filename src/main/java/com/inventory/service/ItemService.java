package com.inventory.service;

import com.inventory.entity.Item;
import com.inventory.model.ItemDTO;
import com.inventory.repository.ItemRepository;
import com.inventory.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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

    public ItemDTO findById(Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        return itemOptional.map(item -> mapper.itemToItemDTO(item)).orElse(null);
    }

    public void createItem(ItemDTO itemDTO) throws IOException {
        Date date = new Date();
        itemDTO.setRegisterDate(date);
        Item item = mapper.itemDTOToItem(itemDTO);
        item.setImage(itemDTO.getImage().getBytes());
        itemRepository.save(item);
    }


    public Page<ItemDTO> findPaginated(Pageable pageable) {

        List<ItemDTO> items = mapper.itemsToItemsDTOs(itemRepository.findAll());
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ItemDTO> list;

        if (items.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, items.size());
            list = items.subList(startItem, toIndex);
        }

        Page<ItemDTO> itemPage = new PageImpl<ItemDTO>(list, PageRequest.of(currentPage, pageSize), items.size());

        return itemPage;

    }
}
