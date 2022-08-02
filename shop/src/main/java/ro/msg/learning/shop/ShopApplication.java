package ro.msg.learning.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(ShopApplication.class, args);
        var beans = context.getBeanDefinitionNames();
        for (var bean : beans) {
            System.out.println(bean);
        }
    }

}
