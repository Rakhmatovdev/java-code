package uz.pdp.app_spring_boot_fastfood_online.service;

import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.BasketProductCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.BasketProductDTO;

import java.util.List;

public interface BasketProductService {

    ApiResult<BasketProductDTO> create(BasketProductCrudDTO crudDTO);

    ApiResult<List<BasketProductDTO>> read();

    ApiResult<BasketProductDTO> readOne(Long id);

    ApiResult<BasketProductDTO> update(BasketProductCrudDTO crudDTO);

    ApiResult<List<BasketProductDTO>> getByBasketId(Long userId);

    ApiResult<String> delete(Long id);

    ApiResult<String> deleteAll(Long userId);




}
