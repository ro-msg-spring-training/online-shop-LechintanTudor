package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.controller.mapper.ProductOrderMapper;
import ro.msg.learning.shop.dto.ProductOrderDto;
import ro.msg.learning.shop.service.ProductOrderService;

@RestController
@RequestMapping("/orders")
public class ProductOrderController {
    private final ProductOrderMapper orderMapper;
    private final ProductOrderService orderService;

    public ProductOrderController(ProductOrderMapper orderMapper, ProductOrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @PostMapping
    public ProductOrderDto saveOrder(@RequestBody ProductOrderDto orderDto) {
        var order = orderService.saveProductOrder(orderMapper.toProductOrder(orderDto));
        return orderMapper.toProductOrderDto(order);
    }
}
