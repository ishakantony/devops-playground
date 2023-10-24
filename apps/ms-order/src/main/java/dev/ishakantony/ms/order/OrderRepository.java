package dev.ishakantony.ms.order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderApplication.Order, Integer> {
}
