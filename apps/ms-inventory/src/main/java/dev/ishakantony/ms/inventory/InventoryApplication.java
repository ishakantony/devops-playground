package dev.ishakantony.ms.inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(basePackages = "dev.ishakantony.ms.inventory")
public class InventoryApplication {

    public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

    @Autowired
    public InventoryRepository inventoryRepository;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "inventory")
    public static class Inventory {
        @Id
        private Integer id;
        private Integer stock;
    }

    @RestController
    class HelloController {

        private static final Logger log = LoggerFactory.getLogger(HelloController.class);
        @GetMapping("/stocks/by-product-id/{id}")
        public Inventory products(@PathVariable int id) {
            if(inventoryRepository.findById(id).isPresent()){
                log.info("product is exist in database");
                return inventoryRepository.findById(id).get();
            }

            log.info("product is NOT exist in database");
            return new Inventory(id,0);
        }

    }

}
