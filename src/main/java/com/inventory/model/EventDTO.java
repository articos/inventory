package com.inventory.model;

import lombok.Data;

@Data
public class EventDTO {

    private Long id;
    private String name;
    private ItemDTO itemDTO;

}
