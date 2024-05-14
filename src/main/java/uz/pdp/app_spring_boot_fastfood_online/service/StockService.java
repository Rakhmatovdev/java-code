package uz.pdp.app_spring_boot_fastfood_online.service;

import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.StockCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.StockDTO;

import java.util.List;

public interface StockService {

    ApiResult<StockDTO> create(StockCrudDTO crudDTO);

    ApiResult<StockDTO> readById(Long id);

    ApiResult<List<StockDTO>> read();

    ApiResult<StockDTO> update(Long id, StockCrudDTO crudDTO);

    ApiResult<String> delete(Long id);

}
