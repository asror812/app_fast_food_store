package com.example.app_fast_food.product;

import com.example.app_fast_food.attachment.AttachmentDTOMapper;
import com.example.app_fast_food.bonus.BonusDTOMapper;
import com.example.app_fast_food.bonus.BonusRepository;
import com.example.app_fast_food.category.CategoryDTOMapper;
import com.example.app_fast_food.comment.CommentDTOMapper;
import com.example.app_fast_food.common.exceptions.RestException;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.discount.DiscountMapper;
import com.example.app_fast_food.discount.DiscountRepository;
import com.example.app_fast_food.product.dto.ProductCreateDTO;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import com.example.app_fast_food.product.dto.ProductUpdateDTO;
import com.example.app_fast_food.user.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Getter
public class ProductService extends GenericService<Product , UUID, ProductResponseDTO , ProductCreateDTO , ProductUpdateDTO > {

    private final ProductRepository repository;
    private final Class<Product> entityClass = Product.class;
    private final ProductDTOMapper mapper;
    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;
    private final BonusDTOMapper bonusMapper;
    private final BonusRepository bonusRepository;
    private final UserRepository userRepository;
    private final CategoryDTOMapper categoryMapper;
    private final CommentDTOMapper commentDTOMapper;
    private final AttachmentDTOMapper attachmentDTOMapper;

    @Override
    protected CommonResponse<ProductResponseDTO> internalCreate(ProductCreateDTO productCreateDTO) {
        Product entity = mapper.toEntity(productCreateDTO);
        repository.save(entity);
        return CommonResponse.succeed(mapper.toResponseDTO(entity));
    }

    @Override
    protected CommonResponse<ProductResponseDTO> internalUpdate(UUID uuid, ProductUpdateDTO productUpdateDTO) {
        return null;
    }

    public CommonResponse<List<ProductResponseDTO>> getByCategory(String categoryName) {
        List<Product> products = repository.findProductsByCategoryName(categoryName);


        List<ProductResponseDTO> productResponseDTOS = getProductResponseDTOS(products);

        return CommonResponse.succeed(productResponseDTOS);
    }

    public CommonResponse<List<ProductResponseDTO>> get4PopularProducts() {
        List<Product> popularProducts = repository.find4TopSoldProducts();

        List<ProductResponseDTO> productResponseDTOS = getProductResponseDTOS(popularProducts);

        return CommonResponse.succeed(productResponseDTOS);
    }

    public CommonResponse<List<ProductResponseDTO>> getCampaignProducts() {

        List<Product> campaignProducts = repository.getCampaignProducts();
        List<ProductResponseDTO> productResponseDTOS = getProductResponseDTOS(campaignProducts);

        return CommonResponse.succeed(productResponseDTOS);
    }


    public CommonResponse<ProductResponseDTO> getSpecificProduct(UUID id) {
        Product product = repository.findProductById(id)
                .orElseThrow(() ->
                        new RestException.EntityNotFoundException("Product" , id.toString()));

        ProductResponseDTO dto = mapper.toResponseDTO(product);

        return CommonResponse.succeed(dto);
    }



    public List<ProductResponseDTO> getProductResponseDTOS(List<Product> products) {

        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

        for (Product popularProduct : products) {
            productResponseDTOS.add(mapper.toResponseDTO(popularProduct));
        }

        return productResponseDTOS;
    }

}