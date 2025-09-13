package com.nttdata.stock.exception;

import com.nttdata.stock.dto.ErrorDetailDto;
import com.nttdata.stock.utils.DateTimeUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StockSaveException.class)
    public ResponseEntity<ErrorDetailDto> handleStockSave(StockSaveException ex) {
        ErrorDetailDto body = ErrorDetailDto.builder()
                .message(ex.getMessage())
                .dateTime(DateTimeUtil.nowString())
                .build();
        return ResponseEntity.ok(body);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Void> handleNotFound(NotFoundException ex) {
        return ResponseEntity.noContent().build();
    }
}