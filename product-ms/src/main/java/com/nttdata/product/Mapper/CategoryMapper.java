package com.nttdata.product.Mapper;

import com.nttdata.product.Model.DTO.CategoryDTO.CategoryDto;
import com.nttdata.product.Model.DTO.CategoryDTO.CategoryPatchDto;
import com.nttdata.product.Model.DTO.CategoryDTO.CategorySaveRequestDto;
import com.nttdata.product.Model.DTO.CategoryDTO.CategorySaveResponseDto;
import com.nttdata.product.Model.Entity.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    public CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto map(Category category);
    List<CategoryDto> map(List<Category> categories);
    CategorySaveResponseDto toCategorySaveResponseDto(Category category);


    Category map(CategoryDto dto);
    Category toEntity(CategorySaveRequestDto dto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromPatchDto(CategoryPatchDto dto, @MappingTarget Category entity);
}