package uz.pdp.app_spring_boot_fastfood_online.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.app_spring_boot_fastfood_online.entity.Category;
import uz.pdp.app_spring_boot_fastfood_online.payload.CategoryCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.CategoryDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    CategoryDTO toDto(Category category);

    Category toEntity(CategoryCrudDTO crudDTO);

    List<CategoryDTO> toDto(List<Category> categories);

    void update(@MappingTarget Category category, CategoryCrudDTO crudDTO);

}