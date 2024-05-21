package uz.pdp.app_spring_boot_fastfood_online.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.app_spring_boot_fastfood_online.entity.BasketProduct;
import uz.pdp.app_spring_boot_fastfood_online.entity.OrderProduct;
import uz.pdp.app_spring_boot_fastfood_online.payload.OrderProductDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderProductMapper {

    OrderProductDTO toDto(OrderProduct stock);

    OrderProduct toEntity(OrderProductDTO orderProductDTO);

    void update(@MappingTarget OrderProduct stock, OrderProductDTO orderProductDTO);

    List<OrderProductDTO> toDto(List<BasketProduct> basketProduct);

}