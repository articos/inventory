package com.inventory.controller;

import com.inventory.model.EventDTO;
import com.inventory.model.ItemDTO;
import com.inventory.service.EventService;
import com.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class EventController {
    
    @Autowired
    private EventService eventService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/createEventPage/{itemId}")
    public String showCreatEventPage(EventDTO eventDTO, ItemDTO itemDTO, Model model, @PathVariable Long itemId) {
        model.addAttribute("item", itemService.findById(itemId));
        return "create-event";
    }

    @PostMapping("/createEvent")
    public String createEvent(@ModelAttribute("eventDTO") EventDTO eventDTO, ItemDTO itemDTO, BindingResult bindingResult, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            return "/";
        }
        eventService.createEvent(eventDTO,itemDTO);
        return "redirect:itemDetail/" + itemDTO.getItemId();
    }

}
