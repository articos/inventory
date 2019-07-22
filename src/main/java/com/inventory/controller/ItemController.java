package com.inventory.controller;

import com.inventory.model.ItemDTO;
import com.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "index";
    }

    @GetMapping("/itemDetail/{id}")
    public String itemDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("item", itemService.findById(id));
        return "item-detail";
    }

    @PostMapping("/createItem")
    public String createItem(@ModelAttribute("itemDTO") ItemDTO itemDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        itemService.createItem(itemDTO);
        return "index";
    }

    @GetMapping("createItemPage")
    public String showCreatItemPage(ItemDTO itemDTO) {
        return "create-item";
    }
}
