package com.nttdata.stock.Controller;

import com.nttdata.stock.dto.FindByProductIdDto;
import com.nttdata.stock.dto.SaveStockRequestDto;
import com.nttdata.stock.dto.SaveStockResponseDto;
import com.nttdata.stock.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    @PostMapping
    public ResponseEntity<List<SaveStockResponseDto>> saveStock(
            @RequestBody @Valid List<SaveStockRequestDto> body) {

        List<SaveStockResponseDto> response = stockService.saveAll(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<FindByProductIdDto> stockByProductId(@PathVariable Integer productId) {
        FindByProductIdDto dto = stockService.findTotalByProductId(productId);
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dto);
    }
}