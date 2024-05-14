package uz.pdp.app_spring_boot_fastfood_online.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.app_spring_boot_fastfood_online.entity.Stock;
import uz.pdp.app_spring_boot_fastfood_online.payload.StockCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.StockDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StockMapper {

    StockDTO toDto(Stock stock);

    Stock toEntity(StockCrudDTO crudDTO);

    List<StockDTO> toDto(List<Stock> categories);

    void update(@MappingTarget Stock stock, StockCrudDTO crudDTO);

}