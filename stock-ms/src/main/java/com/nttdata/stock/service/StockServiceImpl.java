package com.nttdata.stock.service;


import com.nttdata.stock.Entity.Stock;
import com.nttdata.stock.Mapper.StockMapper;
import com.nttdata.stock.dto.FindByProductIdDto;
import com.nttdata.stock.dto.SaveStockRequestDto;
import com.nttdata.stock.dto.SaveStockResponseDto;
import com.nttdata.stock.exception.StockSaveException;
import com.nttdata.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository repository;
    private final StockMapper mapper;

    @Override
    @Transactional
    public List<SaveStockResponseDto> saveAll(List<SaveStockRequestDto> request) {
        for (SaveStockRequestDto dto : request) {
            if (dto.getQuantity() == null || dto.getQuantity() == 0) {
                throw new StockSaveException("Quantity of product cannot be 0");
            }
            if (dto.getQuantity() < 0) {
                throw new StockSaveException("Quantity cannot be negative");
            }
        }

        try {
            List<Stock> toSave = mapper.toEntityList(request);
            List<Stock> saved   = repository.saveAll(toSave);
            return mapper.toResponseList(saved);
        } catch (DataIntegrityViolationException ex) {
            throw new StockSaveException("Duplicate productId/wareHouseId or integrity violation");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public FindByProductIdDto findTotalByProductId(Integer productId) {
        boolean exists = repository.existsByProductId(productId);
        if (!exists) return null;
        Integer total = repository.sumQuantityByProductId(productId);
        return FindByProductIdDto.builder()
                .productId(productId)
                .total(total == null ? 0 : total)
                .build();
    }
}