package dev.ishakantony.ms.inventory;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository
    extends CrudRepository<InventoryApplication.Inventory, Integer> {

    Optional<InventoryApplication.Inventory> findByProductId(Integer productId);

}
