package dev.ishakantony.ms.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    record NewOrder(Integer productId, Integer quantity) {
    }

    record OrderCreated(Integer orderId) {
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Getter
    static class Order {

        public Order(Integer productId, Integer quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private Integer productId;

        private Integer quantity;

    }

    @Slf4j
    @RestController
    @RequiredArgsConstructor
    static class OrderController {

        private final OrderRepository repository;

        @PostMapping("/orders")
        public OrderCreated createNewOrder(@RequestBody NewOrder newOrder) {

            log.info("New order for ProductId: [{}] with Quantity: [{}]", newOrder.productId(),
                newOrder.quantity());

            // TODO - Check inventory

            Order newOrderCreated = repository.save(new Order(newOrder.productId(),
                newOrder.quantity()));

            return new OrderCreated(newOrderCreated.getId());
        }

    }

}
