package ro.msg.learning.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.controller.mapper.StockMapper;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.service.StockService;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {
    private final StockMapper stockMapper;
    private final StockService stockService;

    public StockController(StockMapper stockMapper, StockService stockService) {
        this.stockMapper = stockMapper;
        this.stockService = stockService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStock(@RequestBody StockDto stockDto) {
        stockService.saveStock(stockMapper.toStock(stockDto));
    }

    @GetMapping
    public List<StockDto> findAllStocks() {
        return stockService.findAllStocks().stream()
            .map(stockMapper::toStockDto)
            .toList();
    }
}
