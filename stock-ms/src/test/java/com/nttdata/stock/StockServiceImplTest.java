package com.nttdata.stock;

import com.nttdata.stock.Entity.Stock;
import com.nttdata.stock.Mapper.StockMapper;
import com.nttdata.stock.dto.FindByProductIdDto;
import com.nttdata.stock.dto.SaveStockRequestDto;
import com.nttdata.stock.dto.SaveStockResponseDto;
import com.nttdata.stock.exception.StockSaveException;
import com.nttdata.stock.repository.StockRepository;
import com.nttdata.stock.service.StockService;
import com.nttdata.stock.service.StockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @Mock
    StockRepository repository;
    @Mock
    StockMapper mapper;

    StockService service;

    @BeforeEach
    void setUp() {
        service = new StockServiceImpl(repository, mapper);
    }



    @Test
    void saveAll_ok_returnsResponses() {
        var req = List.of(
                SaveStockRequestDto.builder().productId(2).warehouseId(1).quantity(7).build(),
                SaveStockRequestDto.builder().productId(2).warehouseId(2).quantity(5).build()
        );

        var entities = List.of(
                Stock.builder().productId(2).warehouseId(1).quantity(7).build(),
                Stock.builder().productId(2).warehouseId(2).quantity(5).build()
        );
        var saved = List.of(
                Stock.builder().id(1).productId(2).warehouseId(1).quantity(7).build(),
                Stock.builder().id(2).productId(2).warehouseId(2).quantity(5).build()
        );
        var responses = List.of(
                SaveStockResponseDto.builder().id(1).productId(2).warehouseId(1).quantity(7).build(),
                SaveStockResponseDto.builder().id(2).productId(2).warehouseId(2).quantity(5).build()
        );

        when(mapper.toEntityList(req)).thenReturn(entities);
        when(repository.saveAll(entities)).thenReturn(saved);
        when(mapper.toResponseList(saved)).thenReturn(responses);

        var out = service.saveAll(req);

        assertThat(out).hasSize(2);
        verify(repository).saveAll(entities);
    }

    @Test
    void saveAll_quantityZero_throwsStockSaveException() {
        var req = List.of(SaveStockRequestDto.builder().productId(3).warehouseId(1).quantity(0).build());
        assertThatThrownBy(() -> service.saveAll(req))
                .isInstanceOf(StockSaveException.class)
                .hasMessageContaining("cannot be 0");
        verifyNoInteractions(repository);
    }

    @Test
    void saveAll_integrityViolation_throwsStockSaveException() {
        var req = List.of(SaveStockRequestDto.builder().productId(2).warehouseId(1).quantity(3).build());
        var entities = List.of(Stock.builder().productId(2).warehouseId(1).quantity(3).build());

        when(mapper.toEntityList(req)).thenReturn(entities);
        when(repository.saveAll(entities)).thenThrow(new DataIntegrityViolationException("dup"));

        assertThatThrownBy(() -> service.saveAll(req))
                .isInstanceOf(StockSaveException.class)
                .hasMessageContaining("integrity");
    }

    @Test
    void findTotalByProductId_none_returnsNull() {
        when(repository.existsByProductId(9999)).thenReturn(false);
        FindByProductIdDto dto = service.findTotalByProductId(9999);
        assertThat(dto).isNull();
    }

    @Test
    void findTotalByProductId_exists_returnsDto() {
        when(repository.existsByProductId(2)).thenReturn(true);
        when(repository.sumQuantityByProductId(2)).thenReturn(12);

        FindByProductIdDto dto = service.findTotalByProductId(2);
        assertThat(dto).isNotNull();
        assertThat(dto.getProductId()).isEqualTo(2);
        assertThat(dto.getTotal()).isEqualTo(12);
    }
}