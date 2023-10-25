package dev.ishakantony.ms.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

    @Bean
    CommandLineRunner seedDb(InventoryRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Random random = new Random();
                for (int i = 1; i <= 50; i++) {
                    int quantity = random.nextInt(100) +
                        1; // Random quantity between 1 and 100 (adjust as needed).
                    repository.save(new Inventory(i, quantity));
                }
            }
        };
    }

    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Table(name = "inventory")
    static class Inventory {

        public Inventory(Integer productId, Integer stock) {
            this.productId = productId;
            this.stock = stock;
        }

        @JsonIgnore
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private Integer productId;

        private Integer stock;

    }

    @Slf4j
    @RestController
    @RequiredArgsConstructor
    static class HelloController {

        private final InventoryRepository inventoryRepository;

        @GetMapping("/stocks/by-product-id/{productId}")
        public Inventory products(@PathVariable int productId) {

            Optional<Inventory> inventoryOptional = inventoryRepository.findByProductId(productId);

            if (inventoryOptional.isPresent()) {
                log.info("product is exist in database");
                return inventoryOptional.get();
            }

            log.info("product is NOT exist in database");
            return new Inventory(productId, 0);
        }

    }

}
