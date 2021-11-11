package com.example.apirest.restapi.entity;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    public long getId() {
        return this.id;
    };
    public String getName () {
        return this.name;
    };

    public void setId (long id) {
        this.id = id;
    }

    public void setName (String name) {
        this.name = name;
    }
}