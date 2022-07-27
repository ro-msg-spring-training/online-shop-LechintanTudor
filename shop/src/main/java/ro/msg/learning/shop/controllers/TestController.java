package ro.msg.learning.shop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.model.Supplier;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/supplier")
    public Supplier supplier() {
        return new Supplier(1L, "Supplier Name");
    }
}
