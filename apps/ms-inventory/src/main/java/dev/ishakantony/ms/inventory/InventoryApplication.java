package dev.ishakantony.ms.inventory;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class InventoryApplication {

    public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

    @Autowired
    public static InventoryRepository inventoryRepository;

    @Entity
    @Data
    public class Inventory {
        int id;
        int stock;
    }

    @RestController
    static class HelloController {

    @GetMapping("/stocks/by-product-id/{id}")
    public Inventory products(@RequestParam int id) {
      return inventoryRepository.findById(id).get();
    }

  }

}
