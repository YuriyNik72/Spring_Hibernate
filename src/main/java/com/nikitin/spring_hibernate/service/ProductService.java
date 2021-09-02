package com.nikitin.spring_hibernate.service;

import com.nikitin.spring_hibernate.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nikitin.spring_hibernate.entity.Product;
import com.nikitin.spring_hibernate.repositories.ProductRepository;

import java.util.List;


@Component
public class ProductService {


    @Autowired
    private ProductDAO dao;

    public void saveOrUpdate(Product product){
        dao.saveOrUpdate(product);
    }

    public Product findById(long id) {
        return dao.findById(id);
    }

    public List<Product> findAll() {
        return dao.findAll();
    }

    public void deleteById(long id){
        dao.deleteById(id);
    }
}
