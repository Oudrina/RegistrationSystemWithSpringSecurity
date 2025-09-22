package com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.controller;

import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.model.Product;
import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();

    }

    @GetMapping("total")
    public int totalProducts() {
        return productService.totalProducts();
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {

        try {
            Product product1 = productService.addProducts(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable Long productId) {
        Product product = productService.getProduct(productId);

        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);

    }

    @DeleteMapping("/remove/{productId}")
    public void removeProducts(@PathVariable Long productId) {
        productService.removeProduct(productId);
    }

    @PutMapping("/update/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestPart Product product , @RequestPart MultipartFile imageFile) throws IOException {
        return productService.updateProductById(productId, product,imageFile);
    }

    @GetMapping("product/search")
    public  ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
       List<Product> products = productService.searchProducts(keyword);
       return  new ResponseEntity<>(products,HttpStatus.OK);
    }

}
