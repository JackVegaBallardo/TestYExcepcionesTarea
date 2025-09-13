package com.nttdata.stock.service;




import com.nttdata.stock.dto.FindByProductIdDto;
import com.nttdata.stock.dto.SaveStockRequestDto;
import com.nttdata.stock.dto.SaveStockResponseDto;

import java.util.List;

public interface StockService {
    List<SaveStockResponseDto> saveAll(List<SaveStockRequestDto> request);
    FindByProductIdDto findTotalByProductId(Integer productId);
}