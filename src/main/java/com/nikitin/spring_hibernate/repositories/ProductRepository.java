package com.nikitin.spring_hibernate.repositories;

import org.springframework.stereotype.Component;
import com.nikitin.spring_hibernate.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private static int count;
    private List<Product> productList=new ArrayList<>();

    public Product getProductById(int id) {
        return this.productList.stream().filter(product -> product.getId() == id)
                .findFirst().orElseGet(() -> {return null;});
    }

    public void addNewProduct(Product product){
        product.setId(count++);
        this.productList.add(product);
    }
    public List<Product> getAllProduct(){
        return this.productList;
    }
}
