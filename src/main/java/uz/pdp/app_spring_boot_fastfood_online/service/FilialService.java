package uz.pdp.app_spring_boot_fastfood_online.service;

import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.FilialCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.FilialDTO;

import java.util.List;

public interface FilialService {

    ApiResult<FilialDTO> create(FilialCrudDTO crudDTO);

    ApiResult<FilialDTO> edit(Long id, FilialCrudDTO crudDTO);

    ApiResult<List<FilialDTO>> read();

    ApiResult<FilialDTO> readOne(Long id);

    ApiResult<String> delete(Long id);

}
