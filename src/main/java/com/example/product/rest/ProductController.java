package com.example.product.rest;

import com.example.api.product.CreateProductCommand;
import com.example.api.product.ProductId;
import com.example.product.command.Product;
import com.example.product.query.ProductEntry;
import com.example.product.query.ProductEntryRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by michael.klos on 19/02/14.
 */
@RestController
@RequestMapping("/rest")
public class ProductController {

    @Autowired
    CommandGateway gateway;

    @Autowired
    ProductEntryRepository productEntryRepository;

    @RequestMapping("/init")
    public void init(){
        gateway.send(new CreateProductCommand(new ProductId(), "Audi", "S3"));
        gateway.send(new CreateProductCommand(new ProductId(),"BMW","M1"));
        gateway.send(new CreateProductCommand(new ProductId(),"Ford","Mondeo"));
        gateway.send(new CreateProductCommand(new ProductId(),"Audi","Q3"));
    }

    @RequestMapping("/product")
    public String createProduct(){
        gateway.send(new CreateProductCommand(new ProductId(),"Audi","S3"));
        return "Product has been created";
    }

    @RequestMapping(value = "/products",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<ProductEntry> getAllProducts() {
        List<ProductEntry> list = productEntryRepository.findAll();
        return list;
    }


}
