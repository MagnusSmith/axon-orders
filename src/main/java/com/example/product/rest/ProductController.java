package com.example.product.rest;

import com.example.api.product.CreateProductCommand;
import com.example.api.product.ProductId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by michael.klos on 19/02/14.
 */
@RestController
public class ProductController {

    @Autowired
    CommandGateway gateway;

    @RequestMapping("/product")
    public String createProduct(){
        gateway.send(new CreateProductCommand(new ProductId(),"Audi","S3"));
        return "Product has been created";
    }

}
