package com.inventory.model;

import java.util.Date;
import java.util.List;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class ItemDTO {

    private Long itemId;
    private String name;
    private Date registerDate;
    private MultipartFile image;
    private String imageToShow;
    private List<EventDTO> events;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getImageToShow() {
        return imageToShow;
    }

    public void setImageToShow(String imageToShow) {
        this.imageToShow = imageToShow;
    }

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }
}
