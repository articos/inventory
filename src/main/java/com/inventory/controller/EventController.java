package com.inventory.controller;

import com.inventory.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EventController {
    
    @Autowired
    private EventService eventService;

}
