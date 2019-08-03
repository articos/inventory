package com.inventory.util;

import com.inventory.entity.Event;
import com.inventory.entity.Item;
import com.inventory.model.EventDTO;
import com.inventory.model.ItemDTO;
import org.mapstruct.*;

import java.util.Base64;
import java.util.List;
import java.util.Set;

import static java.util.Base64.getEncoder;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    @Mapping(source = "id", target = "itemId")
    @Mapping(target = "image", ignore = true)
    ItemDTO itemToItemDTO(Item item);


    List<ItemDTO> itemsToItemsDTOs(List<Item> items);

    @Mapping(target = "image", ignore = true)
    Item itemDTOToItem(ItemDTO itemDTO);

    @AfterMapping
    public default void setImage(Item item, @MappingTarget ItemDTO itemDTO) {
        if (item.getImage() != null) {
            itemDTO.setImageToShow(Base64.getEncoder().encodeToString(item.getImage()));
        }
    }

    EventDTO eventToEventDTO(Event event);

    Event eventDTOToEvent(EventDTO eventDTO);
}
