package com.nttdata.dockerized.postgresql.model.entity;

import com.nttdata.dockerized.postgresql.repository.CategoryRepository.CategoryRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Category(String books) {
        this.name=books;
    }
    public Category(){

    }
}