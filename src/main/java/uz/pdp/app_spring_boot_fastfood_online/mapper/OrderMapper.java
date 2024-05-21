package uz.pdp.app_spring_boot_fastfood_online.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.app_spring_boot_fastfood_online.entity.Order;
import uz.pdp.app_spring_boot_fastfood_online.payload.OrderCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.OrderDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderDTO toDto(Order order);

    Order toEntity(OrderCrudDTO crudDTO);

    List<OrderDTO> toDto(List<Order> categories);

    void update(@MappingTarget Order order, OrderCrudDTO crudDTO);

}