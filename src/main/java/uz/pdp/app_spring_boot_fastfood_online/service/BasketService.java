package uz.pdp.app_spring_boot_fastfood_online.service;

import uz.pdp.app_spring_boot_fastfood_online.entity.User;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.BasketCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.BasketDTO;

import java.util.List;

public interface BasketService {

    ApiResult<BasketDTO> create(User user);

    ApiResult<BasketDTO> update(BasketCrudDTO crudDTO);

    ApiResult<String> delete(Long id);

    ApiResult<List<BasketDTO>> read();

    ApiResult<BasketDTO> readOne(Long id);

}
