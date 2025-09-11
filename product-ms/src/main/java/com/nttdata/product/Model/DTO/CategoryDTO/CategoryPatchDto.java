package com.nttdata.product.Model.DTO.CategoryDTO;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryPatchDto {

    @Size(max = 100, message = "name must be <= 100 chars")
    String name;
}
