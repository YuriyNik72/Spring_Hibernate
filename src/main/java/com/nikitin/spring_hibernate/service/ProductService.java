package com.nikitin.spring_hibernate.service;

import com.nikitin.spring_hibernate.entity.Product;
import com.nikitin.spring_hibernate.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductService {


    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    //    @Autowired
   public Product saveOrUpdate(Product p){
     return productRepository.save(p);

    }


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findFilteredByPrice(String filter) {
        List<Product> res;
        switch (filter){
            case "expMin" : res = productRepository.findByPriceAfter(productRepository.findMinPrice());
            break;
            case "chpMax" : res = productRepository.findByPriceBefore(productRepository.findMaxPrice());
            break;
            case "betwMinMax" : res=productRepository.findByPriceAfterAndPriceBefore(productRepository.findMinPrice(), productRepository.findMaxPrice());
            break;
            default: res = new ArrayList<>();
        }
        return res;
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseGet(null);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
