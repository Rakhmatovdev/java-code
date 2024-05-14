package uz.pdp.app_spring_boot_fastfood_online.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.app_spring_boot_fastfood_online.entity.Courier;
import uz.pdp.app_spring_boot_fastfood_online.payload.CourierCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.CourierDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourierMapper {

    CourierDTO toDTO(Courier courier);

    Courier toEntity(CourierCrudDTO crudDTO);

    void update(@MappingTarget Courier courier, CourierCrudDTO crudDTO);
}
