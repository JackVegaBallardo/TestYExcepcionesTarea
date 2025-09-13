package com.nttdata.stock.Mapper;


import com.nttdata.stock.Entity.Stock;
import com.nttdata.stock.dto.SaveStockRequestDto;
import com.nttdata.stock.dto.SaveStockResponseDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {


    @Mapping(source = "warehouseId", target = "warehouseId")
    @Mapping(source = "productId",   target = "productId")
    @Mapping(target = "id", ignore = true)
    Stock toEntity(SaveStockRequestDto dto);

    List<Stock> toEntityList(List<SaveStockRequestDto> dtos);


    @Mapping(source = "warehouseId", target = "warehouseId")
    @Mapping(source = "productId",   target = "productId")
    SaveStockResponseDto toResponse(Stock entity);

    List<SaveStockResponseDto> toResponseList(List<Stock> entities);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(SaveStockRequestDto dto, @MappingTarget Stock entity);
}