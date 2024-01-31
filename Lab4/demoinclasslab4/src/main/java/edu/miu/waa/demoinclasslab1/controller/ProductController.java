package edu.miu.waa.demoinclasslab1.controller;

import edu.miu.waa.demoinclasslab1.entity.Product;
import edu.miu.waa.demoinclasslab1.entity.User;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin
public class ProductController {


    List<Product> products = new ArrayList<>();
    @PostMapping
    public void save(@RequestBody Product product){
        products = new ArrayList<>();
        products.add(new Product(1L,"iphone-15",2000.00));
        products.add(new Product(2L,"s-24",1200.00));
        products.add(new Product(3L,"pixel",1700.00));
        product.setId(4L);
        products.add(product);
        System.out.println("from backend");
    }
    @GetMapping
    public List<Product> findBy(){
        return products;
    }
}
