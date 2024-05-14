package uz.pdp.app_spring_boot_fastfood_online.service;

import uz.pdp.app_spring_boot_fastfood_online.payload.CategoryCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {

    ApiResult<CategoryDTO> create(CategoryCrudDTO crudDTO);

    ApiResult<CategoryDTO> readById(Long id);

    ApiResult<List<CategoryDTO>> read();

    ApiResult<CategoryDTO> update(Long id, CategoryCrudDTO crudDTO);

    ApiResult<String> delete(Long id);

}
