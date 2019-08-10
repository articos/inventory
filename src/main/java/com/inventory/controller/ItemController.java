package com.inventory.controller;

import com.inventory.model.ItemDTO;
import com.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ItemController {

    @Autowired
    ItemService itemService;


    @GetMapping("/")
    public String index(Model model, @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<ItemDTO> items = itemService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("items", items);
        int totalPages = items.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "index";
    }

    @GetMapping("/itemDetail/{id}")
    public String itemDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("item", itemService.findById(id));
        return "item-detail";
    }

    @PostMapping("/createItem")
    public String createItem(@ModelAttribute("itemDTO") ItemDTO itemDTO, BindingResult bindingResult, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        itemService.createItem(itemDTO);
        return "index";
    }

    @GetMapping("/createItemPage")
    public String showCreatItemPage(ItemDTO itemDTO) {
        return "create-item";
    }
}
