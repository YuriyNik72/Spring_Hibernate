package com.nikitin.spring_hibernate.repositories;

import com.nikitin.spring_hibernate.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product p);
    void deleteById(Long id);


    Optional<Product> findById(Long id);
    List<Product> findAll();

    @Query(value = "select min(price) from #{#entityName}", nativeQuery = true)
    int findMinPrice();


    @Query(value = "select max(price) from #{#entityName}", nativeQuery = true)
    int findMaxPrice();

    List<Product> findByPriceAfter(int min);
    List<Product> findByPriceBefore(int max);
    List<Product> findByPriceAfterAndPriceBefore(int min, int max);
}
