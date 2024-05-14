package uz.pdp.app_spring_boot_fastfood_online.service;

import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.FavouriteProductCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.FavouriteProductDTO;

import java.util.List;

public interface FavouriteProductService {

    ApiResult<FavouriteProductDTO> create(FavouriteProductCrudDTO crudDTO);

    ApiResult<FavouriteProductDTO> readById(Long id);

    ApiResult<List<FavouriteProductDTO>> read();

    ApiResult<FavouriteProductDTO> update(Long id, FavouriteProductCrudDTO crudDTO);

    ApiResult<String> delete(Long id);

    ApiResult<String> deleteAll();

}
