package com.naucratis.naucratis.repository;

import com.naucratis.naucratis.model.transaction.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    @Query(value = "select function('year', createdAt) as year, sum(value) from Purchase where function('year', createdAt)=?1 group by year order by year asc")
    List<Object[]> findSalesIn(int rgx);

    @Query(value = "select distinct function('year', createdAt) as createdAt from Purchase order by createdAt")
    List<Integer> findYears();

    @Query(value = "CALL best_selling_categories(?1)", nativeQuery = true)
    List<Object[]> findSalesCategories(int yea);

    @Query(value = "CALL book_selling_per_month(?1, ?2)", nativeQuery = true)
    List<Object[]> findBookSellingPerMonth(int yea, long isbn);
}
