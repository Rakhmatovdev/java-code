package uz.pdp.app_spring_boot_fastfood_online.service;

import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.CourierCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.CourierDTO;

import java.util.List;

public interface CourierService {

    ApiResult<CourierDTO> create(CourierCrudDTO crudDTO);

    ApiResult<CourierDTO> update(Long id, CourierCrudDTO crudDTO);

    ApiResult<List<CourierDTO>> read();

    ApiResult<String> delete(Long id);

    ApiResult<CourierDTO> readOne(Long id);
}
