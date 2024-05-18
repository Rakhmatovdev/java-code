package uz.pdp.app_spring_boot_fastfood_online.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.app_spring_boot_fastfood_online.entity.BasketProduct;
import uz.pdp.app_spring_boot_fastfood_online.payload.BasketProductCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.BasketProductDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BasketProductMapper {

    BasketProductDTO toDto(BasketProduct basketProduct);

    BasketProduct toEntity(BasketProductCrudDTO crudDTO);

    List<BasketProductDTO> toDto(List<BasketProduct> basketProducts);

    void update(@MappingTarget BasketProduct basketProduct, BasketProductCrudDTO crudDTO);

}