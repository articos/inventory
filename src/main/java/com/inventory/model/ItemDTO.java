package com.inventory.model;

import java.util.Date;
import java.util.List;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ItemDTO {

    private Integer itemId;
    private String name;
    private Date registerDate;
    private MultipartFile image;
    private String imageToShow;
    private List<EventDTO> events;

}
