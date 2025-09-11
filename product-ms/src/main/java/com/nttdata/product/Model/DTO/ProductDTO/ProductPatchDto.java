package com.nttdata.product.Model.DTO.ProductDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPatchDto {
    private String name;
    private Double price;
    private Boolean active;
    private Long categoryId;
}