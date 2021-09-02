package com.nikitin.spring_hibernate.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.nikitin.spring_hibernate.entity.Product;
import com.nikitin.spring_hibernate.service.ProductService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Component
@Controller
public class ProductController {
    @Autowired
    private ProductService service;

    // Показать все продукты
    @RequestMapping("/")
    public String showAll(Model model){
        model.addAttribute("products",service.findAll());
        return "allProducts";
    }

    //  Показать форму для новых продуктов
    @RequestMapping("/newProduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "newProduct";
    }
    //  Добавить новый продукт используя объект из формы
    @RequestMapping(path = "/addProduct", method = POST)
    public String addProduct(@ModelAttribute Product product){
        service.saveOrUpdate(product);
        return "redirect:/";
    }

    // Находит продукт с заданным идентификатором (как часть URL) и возвращает представление «результат» или «не найдено»
    @RequestMapping(path = "/product/{id}", method = GET)
    public String showProductByURLId(Model model, @PathVariable(value = "id") long id) {
        return findProduct(model, id);
    }

    //  Находит продукт с заданным идентификатором (как параметр получения) и возвращает представление «результат» или «не найдено»
    //  Вызов по форме
    @RequestMapping(path = "/findId", method = GET)
    public String showProductByFormId(Model model, @RequestParam long id) {
        return findProduct(model, id);
    }

    private String findProduct(Model model, long id){
        Product p=service.findById(id);
        if (p!=null){
            model.addAttribute("product", p);
            return "singleProduct";
        }
        model.addAttribute("id", id);
        return "not found";
    }

    // Показывает форму редактирования для продукта с заданным идентификатором
    @RequestMapping(path = "/editProduct", method = GET)
    public String editProduct(Model model, @RequestParam("id") long id){
        Product p = service.findById(id);
        model.addAttribute("product", p);
        return "editProduct";
    }

    // Удаляет товар с заданным идентификатором
    @RequestMapping(path = "/deleteProduct", method = GET)
    public String deleteProduct(Model model, @RequestParam("id") long id){
        service.deleteById(id);
        return "redirect:/";
    }
}
