package com.example.web.rest.product;

import com.example.cqrs.api.product.CreateProductCommand;
import com.example.cqrs.api.product.DeleteProductCommand;
import com.example.cqrs.api.product.ProductId;
import com.example.cqrs.api.product.UpdateProductCommand;
import com.example.cqrs.concrete.product.query.ProductFactory;
import com.example.cqrs.concrete.product.query.ProductQueryRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by michael.klos on 19/02/14.
 */
@RestController
@RequestMapping("/rest/products")
public class ProductController {

    @Autowired
    private CommandGateway gateway;

    @Autowired
    private ProductQueryRepository productQueryRepository;


    private void init() {
        gateway.send(new CreateProductCommand(ProductFactory.create("Model 1", "Brand 1")));
        gateway.send(new CreateProductCommand(ProductFactory.create("Model 2", "Brand 2")));
        gateway.send(new CreateProductCommand(ProductFactory.create("Model 3", "Brand 3")));
        gateway.send(new CreateProductCommand(ProductFactory.create("Model 4", "Brand 4")));
    }


    /**
     * Return all products in DB.
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,
            produces = "application/json")
    public List<ProductTO> getAllProducts() {
        return ProductTO.fromProductsDetails(productQueryRepository.findAll());
    }


    /**
     * Get a product.
     *
     * @param aProductId productId;
     * @return
     */
    @RequestMapping(value = "/{aProductId}",
            method = RequestMethod.GET,
            produces = "application/json")
    public ProductTO getProductById(@PathVariable String aProductId) {
        return ProductTO.fromProductDetails(productQueryRepository.findOne(new ProductId(aProductId)));
    }

    /**
     * Delete a product.
     *
     * @param aProductId
     */
    @RequestMapping(value = "/{aProductId}",
            method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable String aProductId) {
        gateway.sendAndWait(new DeleteProductCommand(new ProductId(aProductId)));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateProduct(@RequestBody ProductTO productTO) {
        gateway.sendAndWait(new UpdateProductCommand(productTO.toProductDetails()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createProduct(@RequestBody ProductTO productTO) {
        gateway.sendAndWait(new CreateProductCommand(productTO.toProductDetails()));
    }
}
