package com.inventory.service;

import com.inventory.repository.EvenRepository;
import com.inventory.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EvenRepository evenRepository;

    @Autowired
    private ModelMapper mapper;
}
