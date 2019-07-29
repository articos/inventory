package com.inventory.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

import lombok.Data;

@Entity
@Table(name = "ITEM")
@Data
public class Item {

    @Id
    @Column(name = "ITEM_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "REGISTER_DATE")
    private Date registerDate;

    @Lob
    private byte[] image;

    @OneToMany(mappedBy="item")
    private Set<Event> events;

}
