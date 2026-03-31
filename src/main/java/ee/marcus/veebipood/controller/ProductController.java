package ee.marcus.veebipood.controller;

import ee.marcus.veebipood.entity.Product;
import ee.marcus.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins= "*") // paris arenduses * ei kasutaks

@RestController
public class ProductController {

    // localhost:8090/products
    // application.properties server.port=8090
//    @GetMapping("products")
//    public String getProducts() {
//        return "Hello World";
//    }

    //1xx informatiivne
    //2xx õnnestumine
    //3xx redirect
    //4xx päringu tegija viga (client error / frontend error)
    //5xx päringu vastaja viga (server error)

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public Product getOneProducts(@PathVariable Long id){
        return productRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product){
        if (product.getId()!=null) {
            throw new RuntimeException("Can not add with ID");
        }
        productRepository.save(product);
        return productRepository.findAll();
    }

    @PutMapping("products/{id}")
    public List<Product> editProduct(@RequestBody Product product){
        if (product.getId()==null) {
            throw new RuntimeException("Can not edit without ID");
        }
        if (!productRepository.existsById(product.getId())) {
            throw new RuntimeException("Product ID does not exist");
        }
        productRepository.save(product);
        return productRepository.findAll();
    }

}