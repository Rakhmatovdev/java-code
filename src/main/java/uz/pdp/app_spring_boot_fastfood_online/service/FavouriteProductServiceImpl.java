package uz.pdp.app_spring_boot_fastfood_online.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app_spring_boot_fastfood_online.entity.FavouriteProduct;
import uz.pdp.app_spring_boot_fastfood_online.entity.Product;
import uz.pdp.app_spring_boot_fastfood_online.entity.User;
import uz.pdp.app_spring_boot_fastfood_online.exception.RestException;
import uz.pdp.app_spring_boot_fastfood_online.mapper.FavouriteProductMapper;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.FavouriteProductCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.FavouriteProductDTO;
import uz.pdp.app_spring_boot_fastfood_online.repository.FavouriteProductRepository;
import uz.pdp.app_spring_boot_fastfood_online.repository.ProductRepository;
import uz.pdp.app_spring_boot_fastfood_online.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteProductServiceImpl implements FavouriteProductService{

    private final FavouriteProductRepository favouriteProductRepository;

    private final FavouriteProductMapper favouriteProductMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public ApiResult<FavouriteProductDTO> create(FavouriteProductCrudDTO crudDTO) {

        User user = userRepository.findById(crudDTO.getUserId())
                .orElseThrow(() -> RestException.notFound("User with id: " + crudDTO.getUserId()));

        Product product = productRepository.findById(crudDTO.getProductId())
                .orElseThrow(() -> RestException.notFound("Product with id: " + crudDTO.getProductId()));

        FavouriteProduct entity = favouriteProductMapper.toEntity(crudDTO);

        entity.setProduct(product);

        entity.setUser(user);

        FavouriteProduct save = favouriteProductRepository.save(entity);

        return ApiResult.success(favouriteProductMapper.toDto(save));
    }

    @Override
    public ApiResult<FavouriteProductDTO> readById(Long id) {

        FavouriteProduct favouriteProduct = favouriteProductRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Product with id: " + id));

        return ApiResult.success(favouriteProductMapper.toDto(favouriteProduct));
    }

    @Override
    public ApiResult<List<FavouriteProductDTO>> read() {

        List<FavouriteProduct> favouriteProducts = favouriteProductRepository.findAll();

        return ApiResult.success(favouriteProductMapper.toDto(favouriteProducts));
    }

    @Override
    public ApiResult<FavouriteProductDTO> update(Long id, FavouriteProductCrudDTO crudDTO) {

        FavouriteProduct favouriteProduct = favouriteProductRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Product with id: " + id));

        User user = userRepository.findById(crudDTO.getUserId())
                .orElseThrow(() -> RestException.notFound("User with id: " + crudDTO.getUserId()));

        Product product = productRepository.findById(crudDTO.getProductId())
                .orElseThrow(() -> RestException.notFound("Product with id: " + crudDTO.getProductId()));


        favouriteProduct.setProduct(product);

        favouriteProduct.setUser(user);

        FavouriteProduct save = favouriteProductRepository.save(favouriteProduct);

        return ApiResult.success(favouriteProductMapper.toDto(save));
    }

    @Override
    public ApiResult<String> delete(Long id) {

        favouriteProductRepository.deleteById(id);

        return ApiResult.success("Done");
    }

    @Override
    public ApiResult<String> deleteAll() {

      favouriteProductRepository.deleteAll();

        return ApiResult.success("All favourite products have been deleted");
    }
}
