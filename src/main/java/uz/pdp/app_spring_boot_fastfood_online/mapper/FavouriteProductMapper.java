package uz.pdp.app_spring_boot_fastfood_online.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.app_spring_boot_fastfood_online.entity.FavouriteProduct;
import uz.pdp.app_spring_boot_fastfood_online.payload.FavouriteProductCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.FavouriteProductDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FavouriteProductMapper {

    FavouriteProductDTO toDto(FavouriteProduct stock);

    FavouriteProduct toEntity(FavouriteProductCrudDTO crudDTO);

    List<FavouriteProductDTO> toDto(List<FavouriteProduct> categories);

    void update(@MappingTarget FavouriteProduct stock, FavouriteProductCrudDTO crudDTO);

}