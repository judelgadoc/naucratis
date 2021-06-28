package com.naucratis.naucratis.service;

import com.naucratis.naucratis.model.transaction.Purchase;
import com.naucratis.naucratis.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PurchaseService {

    private PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository)
    {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Object[]> getSalesIn(int year) {

        return purchaseRepository.findSalesIn(year);
    }

    public List<Integer> getYears() {
        return purchaseRepository.findYears();
    }

    public List<Object[]> getSalesCategories(int year) {
        return purchaseRepository.findSalesCategories(year);
    }

    public List<Object[]> getBookSalesPerMonth(int year, long isbn) {
        return purchaseRepository.findBookSellingPerMonth(year, isbn);
    }
}