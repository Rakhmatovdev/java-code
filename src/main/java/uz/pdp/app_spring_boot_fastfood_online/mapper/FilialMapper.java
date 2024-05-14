package uz.pdp.app_spring_boot_fastfood_online.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.app_spring_boot_fastfood_online.entity.Filial;
import uz.pdp.app_spring_boot_fastfood_online.payload.FilialCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.FilialDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FilialMapper {

    FilialDTO toDTO(Filial filial);

    Filial toEntity(FilialCrudDTO crudDTO);

    void updateEntity(@MappingTarget Filial filial, FilialCrudDTO crudDTO);
}
