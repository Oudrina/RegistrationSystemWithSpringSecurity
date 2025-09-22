package com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.service;

import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.model.Product;
import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(RuntimeException::new);
    }

    public Product addProducts(Product product, MultipartFile imageFile) throws IOException {

        product.setImageUrlName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return productRepository.save(product);
    }

    public void removeProduct(Long productId) {
        productRepository.deleteById(productId);
    }


    public Product updateProductById(Long productId, Product updatedProduct , MultipartFile imageFile) throws IOException {
        Product existingProduct = productRepository
                .findById(productId)
                .orElseThrow(() -> new RuntimeException("Product Id not  found"));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());

        existingProduct.setImageUrlName(imageFile.getOriginalFilename());
        existingProduct.setImageType(imageFile.getContentType());
        existingProduct.setImageData(imageFile.getBytes());

        return productRepository.save(existingProduct);


    }


    public int totalProducts() {
         List<Product> products =productRepository.findAll();

         return  products.size();
    }

    public List<Product> searchProducts(String keyword) {
        return  productRepository.searchProductByKeyword(keyword);
    }
}
