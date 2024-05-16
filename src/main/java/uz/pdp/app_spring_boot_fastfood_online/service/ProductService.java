package uz.pdp.app_spring_boot_fastfood_online.service;

import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.ProductCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.ProductDTO;

import java.util.List;

public interface ProductService {

    ApiResult<ProductDTO> create(ProductCrudDTO crudDTO);

    ApiResult<ProductDTO> readById(Long id);

    ApiResult<List<ProductDTO>> read();

    ApiResult<ProductDTO> update(Long id, ProductCrudDTO crudDTO);

    ApiResult<String> delete(Long id);

    ApiResult<List<ProductDTO>> readAllProductsWithStock();

}
