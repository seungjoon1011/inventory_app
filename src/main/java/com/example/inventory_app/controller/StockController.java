package com.example.inventory_app.controller;

import com.example.inventory_app.dto.StockDto;
import com.example.inventory_app.dto.StockUpdateDto;
import com.example.inventory_app.model.Stock;
import com.example.inventory_app.query.StockSearchCondition;
import com.example.inventory_app.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @PostMapping
    public Stock create(@Valid @RequestBody StockDto dto) {
        return stockService.create(dto);
    }
    @GetMapping
    public List<Stock> search(StockSearchCondition condition) {
        return stockService.search(condition);
    }
    @GetMapping("/{id}")
    public Stock getById(@PathVariable Long id) {
        return stockService.getById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        stockService.delete(id);
    }
    @PutMapping("/{id}")
    public Stock update(@PathVariable Long id, @Valid @RequestBody StockUpdateDto dto) {
        return stockService.update(id, dto);
    }
    @PostMapping("/transfer")
    public void transfer(
            @RequestParam Long productId,
            @RequestParam Long srcWarehouseId,
            @RequestParam Long destWarehouseId,
            @RequestParam int quantity
    ) {
        stockService.transferStock(productId, srcWarehouseId, destWarehouseId, quantity);
    }
}