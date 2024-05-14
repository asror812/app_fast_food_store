package com.example.app_fast_food.product;

import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.product.dto.ProductCreateDTO;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import com.example.app_fast_food.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PostMapping("/product")
    public CommonResponse<ProductResponseDTO> addProduct(@RequestBody ProductCreateDTO createDTO) {
        return productService.create(createDTO);
    }

 /*   @GetMapping("/product/{id}")
    public CommonResponse<ProductResponseDTO> getById(@PathVariable UUID id) {
        return productService.getById(id);
    }
 */

    @GetMapping("/products")
    public CommonResponse<List<ProductResponseDTO>> getAll() {
        return productService.getAll();
    }

    @GetMapping("/menu/{categoryName}")
    public CommonResponse<List<ProductResponseDTO>> getAllByCategory(@PathVariable String categoryName) {
          return productService.getByCategory(categoryName);
    }

    @GetMapping("/main")
    public CommonResponse<List<ProductResponseDTO>> get4PopularProducts(){
        return productService.get4PopularProducts();
    }

    @GetMapping("/campaign")
    public CommonResponse<List<ProductResponseDTO>> getCampaignProducts() {
        return productService.getCampaignProducts();
    }

    @GetMapping("/product/{id}")
    public CommonResponse<ProductResponseDTO> getProductIndividual(@PathVariable UUID id) {
        return productService.getSpecificProduct(id);
    }



}
