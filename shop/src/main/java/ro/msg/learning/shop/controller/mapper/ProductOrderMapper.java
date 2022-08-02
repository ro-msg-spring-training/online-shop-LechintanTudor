package ro.msg.learning.shop.controller.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductOrderDto;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.ProductOrder;

import java.util.stream.Collectors;

@Component
public class ProductOrderMapper {
    private final ProductOrderDetailMapper productOrderDetailMapper;

    public ProductOrderMapper(ProductOrderDetailMapper productOrderDetailMapper) {
        this.productOrderDetailMapper = productOrderDetailMapper;
    }

    public ProductOrder toProductOrder(ProductOrderDto productOrderDto) {
        var customer = Customer.builder()
            .id(productOrderDto.getCustomerId())
            .build();

        var details = productOrderDto.getDetails().stream()
            .map(productOrderDetailMapper::toProductOrderDetail)
            .collect(Collectors.toSet());

        return ProductOrder.builder()
            .id(productOrderDto.getId())
            .customer(customer)
            .createdAt(productOrderDto.getCreatedAt())
            .address(productOrderDto.getAddress())
            .details(details)
            .build();
    }

    public ProductOrderDto toProductOrderDto(ProductOrder productOrder) {
        var details = productOrder.getDetails().stream()
            .map(productOrderDetailMapper::toProductOrderDetailDto)
            .collect(Collectors.toSet());

        return ProductOrderDto.builder()
            .id(productOrder.getId())
            .customerId(productOrder.getCustomer().getId())
            .createdAt(productOrder.getCreatedAt())
            .address(productOrder.getAddress())
            .details(details)
            .build();
    }
}
