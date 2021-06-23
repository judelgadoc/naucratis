package com.naucratis.naucratis.repository;

import com.naucratis.naucratis.model.transaction.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}
