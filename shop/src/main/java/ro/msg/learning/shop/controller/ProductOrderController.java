package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.ProductOrderDto;
import ro.msg.learning.shop.model.info.ProductOrderInfo;
import ro.msg.learning.shop.service.ProductOrderService;

@RestController
@RequestMapping("/orders")
public class ProductOrderController {
    private final ProductOrderService orderService;

    public ProductOrderController(ProductOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ProductOrderDto saveOrder(@RequestBody ProductOrderInfo order) {
        return new ProductOrderDto(orderService.saveProductOrder(order));
    }
}
