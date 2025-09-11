package com.nttdata.product.Model.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Double price;

    @Column
    private Boolean active;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
   public Product(){

   }
    public Product(String ddd, Category books) {
       this.name=ddd;
       this.category=books;
    }
}