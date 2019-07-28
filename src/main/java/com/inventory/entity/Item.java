package com.inventory.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "ITEM")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
