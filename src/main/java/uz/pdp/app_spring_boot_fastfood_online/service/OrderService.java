package uz.pdp.app_spring_boot_fastfood_online.service;

import uz.pdp.app_spring_boot_fastfood_online.payload.OrderCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.OrderDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.OrderHistoryDTO;

import java.util.List;

public interface OrderService {

    ApiResult<OrderDTO> create(OrderCrudDTO crudDTO);

    ApiResult<List<OrderDTO>> read(Long userId);

    ApiResult<OrderDTO> readOne(Long orderId);

    ApiResult<List<OrderHistoryDTO>> ordersHistory(Long userId);


}
