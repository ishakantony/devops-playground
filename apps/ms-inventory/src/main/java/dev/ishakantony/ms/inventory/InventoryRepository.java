package dev.ishakantony.ms.inventory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<InventoryApplication.Inventory, Integer> {

}
