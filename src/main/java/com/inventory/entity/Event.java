package com.inventory.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "EVENT")
@Data
public class Event {

    @Id
    @Column(name = "EVENT_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

}
