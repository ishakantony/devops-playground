package dev.ishakantony.ms.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    record NewOrder(Integer productId, Integer quantity) {
    }

    record OrderCreated(Integer orderId) {
    }

    record Error(String message) { }

    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Getter
    @Table(name = "orders")
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
    @Service
    static class InventoryService {

        @JsonIgnoreProperties(ignoreUnknown = true)
        record Stock(Integer stock) {
        }

        private final RestTemplate restTemplate;

        private final String baseUrl;

        public InventoryService(RestTemplateBuilder restTemplateBuilder, Environment environment) {
            this.restTemplate = restTemplateBuilder.build();
            this.baseUrl =
                environment.getProperty("INVENTORY_SERVICE_BASE_URL", "http://localhost:8083");
        }

        public Integer findStockByProductId(int productId) {
            try {
                Stock stock = Optional.ofNullable(restTemplate.getForObject(
                    this.baseUrl + String.format("/stocks/by-product-id/%s", productId),
                    Stock.class)).orElse(new Stock(0));
                return stock.stock();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return 0;
            }
        }

    }

    @Slf4j
    @RestController
    @RequiredArgsConstructor
    static class OrderController {

        private final OrderRepository repository;

        private final InventoryService inventory;

        @PostMapping("/orders")
        public ResponseEntity<?> createNewOrder(@RequestBody NewOrder newOrder) {

            log.info("New order for ProductId: [{}] with Quantity: [{}]", newOrder.productId(),
                newOrder.quantity());

            int stock = inventory.findStockByProductId(newOrder.productId());

            if (stock == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Out of stock"));
            }

            Order newOrderCreated = repository.save(new Order(newOrder.productId(),
                newOrder.quantity()));

            return ResponseEntity.status(HttpStatus.CREATED).body(new OrderCreated(newOrderCreated.getId()));
        }

    }

}
