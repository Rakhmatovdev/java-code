package uz.pdp.app_spring_boot_fastfood_online.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app_spring_boot_fastfood_online.entity.Basket;
import uz.pdp.app_spring_boot_fastfood_online.entity.BasketProduct;
import uz.pdp.app_spring_boot_fastfood_online.entity.Product;
import uz.pdp.app_spring_boot_fastfood_online.exception.RestException;
import uz.pdp.app_spring_boot_fastfood_online.mapper.BasketProductMapper;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.BasketProductCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.BasketProductDTO;
import uz.pdp.app_spring_boot_fastfood_online.repository.BasketProductRepository;
import uz.pdp.app_spring_boot_fastfood_online.repository.BasketRepository;
import uz.pdp.app_spring_boot_fastfood_online.repository.ProductRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BasketProductServiceImpl implements BasketProductService {

    private final BasketProductRepository basketProductRepository;

    private final BasketProductMapper basketProductMapper;
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    @Override
    public ApiResult<BasketProductDTO> create(BasketProductCrudDTO crudDTO) {

        BasketProduct entity = basketProductMapper.toEntity(crudDTO);

        Basket basket = basketRepository.findByUserIdAndIsClosedFalse(crudDTO.getUserId())
                .orElseThrow(() -> RestException.restThrow("Something went wrong, please try again"));

        Product product = productRepository.findById(crudDTO.getProductId())
                .orElseThrow(() -> RestException.notFound("Any product with id: " + crudDTO.getProductId()));

        entity.setBasket(basket);
        entity.setProduct(product);
        entity.setProductName(product.getName());
        entity.setBuyingPrice(product.getPrice());

        BasketProduct saved = basketProductRepository.save(entity);

        return ApiResult.success(basketProductMapper.toDto(saved));
    }

    @Override
    public ApiResult<List<BasketProductDTO>> read() {

        return ApiResult.success(basketProductMapper.toDto(basketProductRepository.findAll()));
    }

    @Override
    public ApiResult<BasketProductDTO> readOne(Long id) {

        BasketProduct basketProduct = basketProductRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Any basketProduct with id: " + id));

        return ApiResult.success(basketProductMapper.toDto(basketProduct));
    }

    @Override
    public ApiResult<BasketProductDTO> update( BasketProductCrudDTO crudDTO) {

        BasketProduct basketProduct = basketProductRepository.findByProductId(crudDTO.getProductId())
                .orElseThrow(() -> RestException.notFound("Something went wrong, please try again"));

        if(Objects.equals(crudDTO.getQuantity(),0)) {
            basketProductRepository.delete(basketProduct);
            throw RestException.restThrow("The product is removed from the basket");
        }

        basketProduct.setQuantity(crudDTO.getQuantity());

        BasketProduct saved = basketProductRepository.save(basketProduct);

        return ApiResult.success(basketProductMapper.toDto(saved));
    }

    @Override
    public ApiResult<List<BasketProductDTO>> getByBasketId(Long userId) {

        Basket basket = basketRepository.findByUserIdAndIsClosedFalse(userId)
                .orElseThrow(() -> RestException.restThrow("Something went wrong, please try again"));

        List<BasketProduct> basketProducts = basketProductRepository.findAllByBasketId(basket.getId());

        return ApiResult.success(basketProductMapper.toDto(basketProducts));
    }

    @Override
    public ApiResult<String> delete(Long id) {

        basketProductRepository.deleteById(id);

        return ApiResult.success("BasketProduct with id: " + id+ " is deleted successfully");
    }

    @Override
    public ApiResult<String> deleteAll(Long userId) {

        Basket basket = basketRepository.findByUserIdAndIsClosedFalse(userId)
                .orElseThrow(() -> RestException.restThrow("Something went wrong, please try again"));

        basketProductRepository.deleteAllByBasketId(basket.getId());

        return ApiResult.success("Basket is cleaned !");
    }
}
