package com.example.web.rest.product;

import com.example.api.product.CreateProductCommand;
import com.example.api.product.DeleteProductCommand;
import com.example.api.product.ProductId;
import com.example.product.command.Product;
import com.example.product.query.ProductEntry;
import com.example.product.query.ProductQueryRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    private CommandGateway gateway;

    @Autowired
    private ProductQueryRepository productQueryRepository;


    private void init(){
        gateway.send(new CreateProductCommand(new ProductId(), "Model 1", "Brand 1"));
        gateway.send(new CreateProductCommand(new ProductId(),"Model 2","Brand 2"));
        gateway.send(new CreateProductCommand(new ProductId(),"Model 3","Brand 3"));
        gateway.send(new CreateProductCommand(new ProductId(),"Model 4","Brand 4"));
    }

//    @RequestMapping("/product")
//    public String createProduct(){
//        gateway.send(new CreateProductCommand(new ProductId(),"Audi","S3"));
//        return "Product has been created";
//    }

    /**
     * Return all products in DB.
     * @return
     */
    @RequestMapping(value = "/products",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<ProductEntry> getAllProducts() {
        //TODO for test only
        if(productQueryRepository.count() == 0){
            init();
        }
        return productQueryRepository.findAll();
    }


    /**
     * Get a product.
     * @param aProductId productId;
     * @return
     */
    @RequestMapping(value = "/products/{aProductId}",
            method = RequestMethod.GET,
            produces = "application/json")
    public ProductEntry getProductById(@PathVariable String aProductId) {
        ProductId productId = new ProductId(aProductId);
        return productQueryRepository.findOne(productId);
    }

    /**
     * Delete a product.
     * @param aProductId
     */
    @RequestMapping(value = "/products/{aProductId}",
            method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable String aProductId) {
        ProductId productId = new ProductId(aProductId);
        gateway.sendAndWait(new DeleteProductCommand(productId));
    }
}
