package com.nttdata.product.Model.DTO.CategoryDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategorySaveRequestDto {
    @NotBlank(message = "name is required")
    @Size(max = 100, message = "name must be <= 100 chars")
    private String name;
}
