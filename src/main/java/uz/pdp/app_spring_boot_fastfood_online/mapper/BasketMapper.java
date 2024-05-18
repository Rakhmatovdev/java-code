package uz.pdp.app_spring_boot_fastfood_online.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.app_spring_boot_fastfood_online.entity.Basket;
import uz.pdp.app_spring_boot_fastfood_online.payload.BasketCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.BasketDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BasketMapper {


    BasketDTO toDto(Basket basket);

    Basket toEntity(BasketCrudDTO crudDTO);

    List<BasketDTO> toDto(List<Basket> baskets);

    void update(@MappingTarget Basket basket, BasketCrudDTO crudDTO);


}
