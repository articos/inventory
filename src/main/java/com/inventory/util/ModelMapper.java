package com.inventory.util;

import com.inventory.entity.Item;
import com.inventory.model.ItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    @Mapping(source = "id", target = "itemId")
    ItemDTO itemToItemDTO(Item item);

    List<ItemDTO> itemsToItemsDTOs(List<Item> items);

    Item itemDTOToItem(ItemDTO itemDTO);
}
