package uz.pdp.app_spring_boot_fastfood_online.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app_spring_boot_fastfood_online.entity.Category;
import uz.pdp.app_spring_boot_fastfood_online.payload.CategoryCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.exception.RestException;
import uz.pdp.app_spring_boot_fastfood_online.mapper.CategoryMapper;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.CategoryDTO;
import uz.pdp.app_spring_boot_fastfood_online.repository.CategoryRepository;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;


    @Override
    public ApiResult<CategoryDTO> create(CategoryCrudDTO crudDTO) {

        Category entity = categoryMapper.toEntity(crudDTO);

        if(Objects.nonNull(crudDTO.getParentCategoryId())) {
            Category category = categoryRepository.findById(crudDTO.getParentCategoryId())
                    .orElseThrow(() -> RestException.notFound("Parent category with id " + crudDTO.getParentCategoryId()));
            entity.setParentCategory(category);
        }

        Category save = categoryRepository.save(entity);

        return ApiResult.success(categoryMapper.toDto(save));
    }

    @Override
    public ApiResult<CategoryDTO> readById(Long id) {
        return ApiResult.success(categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Category with id " + id )
        )));
    }

    @Override
    public ApiResult<List<CategoryDTO>> read() {
        return ApiResult.success(categoryMapper.toDto(categoryRepository.findAll()));
    }

    @Override
    public ApiResult<CategoryDTO> update(Long id, CategoryCrudDTO crudDTO) {

        Category entity = categoryRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Category with id " + id ));

        categoryMapper.update(entity,crudDTO);

        if(Objects.nonNull(crudDTO.getParentCategoryId())) {
            Category category = categoryRepository.findById(crudDTO.getParentCategoryId())
                    .orElseThrow(() -> RestException.notFound("Parent category with id " + crudDTO.getParentCategoryId()));
            entity.setParentCategory(category);
        }

        return ApiResult.success(categoryMapper.toDto(entity));
    }

    @Override
    public ApiResult<String> delete(Long id) {

        categoryRepository.deleteById(id);

        return ApiResult.success("Category with id " + id + " is deleted successfully");
    }
}
