package com.naucratis.naucratis.repository;

import com.naucratis.naucratis.model.transaction.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
