package com.inventory.service;

import com.inventory.entity.Item;
import com.inventory.model.ItemDTO;
import com.inventory.repository.ItemRepository;
import com.inventory.util.ModelMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

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

    //TODO refactor not found image
    public Page<ItemDTO> findPaginated(Pageable pageable) throws IOException {

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
        byte[] fileContent = FileUtils.readFileToByteArray(new File("src/main/resources/static/images/not-found-image.png"));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        for (ItemDTO item : itemPage) {
            if (item.getImageToShow() == null || item.getImageToShow().isEmpty()) {
                item.setImageToShow(encodedString);
            }
        }

        return itemPage;

    }
}
