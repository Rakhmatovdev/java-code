package uz.pdp.app_spring_boot_fastfood_online.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.OrderCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.OrderDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.OrderHistoryDTO;
import uz.pdp.app_spring_boot_fastfood_online.service.OrderService;
import uz.pdp.app_spring_boot_fastfood_online.utils.AppConstant;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(AppConstant.BASE_PATH_V1+"/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ApiResult<OrderDTO> order(@RequestBody OrderCrudDTO crudDTO) {

        log.info("Request to OrderController order: {}", crudDTO);
        return orderService.create(crudDTO);
    }


    @Operation(method = "read" , description = "Returns all orders of a user")
    @GetMapping("/{userId}")
    public ApiResult<List<OrderDTO>> read(@PathVariable Long userId) {

        log.info("Request to OrderController read: {}", userId);
        return orderService.read(userId);
    }

    @GetMapping("/{orderId}")
    public ApiResult<OrderDTO> readOne(@PathVariable Long orderId) {

        log.info("Request to OrderController readOne: {}", orderId);
        return orderService.readOne(orderId);
    }

    @GetMapping("/orders-history/{userId}")
    public ApiResult<List<OrderHistoryDTO>> ordersHistory(@PathVariable Long userId) {

        log.info("Request to OrderController ordersHistory: {}", userId);
        return orderService.ordersHistory(userId);
    }


}
