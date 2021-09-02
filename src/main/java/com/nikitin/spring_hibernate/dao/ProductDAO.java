package com.nikitin.spring_hibernate.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nikitin.spring_hibernate.entity.Product;
import com.nikitin.spring_hibernate.repositories.ProductRepository;

import java.util.List;

@Component
public class ProductDAO {
    @Autowired
    private ProductRepository repository;


    public Product findById (long id){
        return repository.findById(id).orElse(null);
    }

    public List<Product> findAll(){
        return repository.findAll();
    }

    public void deleteById(long id){
        repository.deleteById(id);
    }

    public void saveOrUpdate(Product product) {
        repository.save(product);
    }
}
