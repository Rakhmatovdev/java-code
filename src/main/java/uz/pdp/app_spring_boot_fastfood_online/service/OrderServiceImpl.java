package uz.pdp.app_spring_boot_fastfood_online.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app_spring_boot_fastfood_online.entity.*;
import uz.pdp.app_spring_boot_fastfood_online.exception.RestException;
import uz.pdp.app_spring_boot_fastfood_online.mapper.OrderMapper;
import uz.pdp.app_spring_boot_fastfood_online.mapper.OrderProductMapper;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.OrderCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.OrderDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.OrderHistoryDTO;
import uz.pdp.app_spring_boot_fastfood_online.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final OrderProductMapper orderProductMapper;
    private final OrderMapper orderMapper;
    private final BonusRepository bonusRepository;
    private final CourierRepository courierRepository;
    private final OrderRepository orderRepository;
    private final BasketServiceImpl basketService;

    @Override
    public ApiResult<OrderDTO> create(OrderCrudDTO crudDTO) {

        User user = userRepository.findById(crudDTO.getUserId())
                .orElseThrow(() -> RestException.notFound("Any user with id " + crudDTO.getUserId()));

        Basket basket = basketRepository.findByUserIdAndIsClosedFalse(crudDTO.getUserId())
                .orElseThrow(() -> RestException.notFound("Any basket with id " + crudDTO.getUserId()));

        Bonus bonus = bonusRepository.findById(crudDTO.getBonusId())
                .orElseThrow(() -> RestException.notFound("Any bonus with id " + crudDTO.getBonusId()));

        List<Courier> couriers = courierRepository.findAll();

        if (couriers.isEmpty())
            throw new RestException("At the moment there is no any courier");

        List<BasketProduct> products = basket.getProducts();

        List<OrderProduct> orderProducts = mapToOrderProduct(products);

        String address;

        if(Objects.isNull(crudDTO.getDeliveryAddress()))
            address = user.getAddress();
        else address = crudDTO.getDeliveryAddress();


        Order entity = orderMapper.toEntity(crudDTO);

        entity.setBonus(bonus);
        entity.setCourier(couriers.get(0));
        entity.setUser(user);
        entity.setOrderDate(new Date());
        entity.setOrderProducts(orderProducts);
        entity.setDeliveryAddress(address);
        entity.setDeliveryCost(9000d);
        entity.setTotalPrice(basket.getTotalPrice()+9000);

        Order save = orderRepository.save(entity);

        basket.setIsClosed(true);
        basketRepository.save(basket);
        basketService.create(user);


        return ApiResult.success(orderMapper.toDto(save));
    }



    @Override
    public ApiResult<List<OrderDTO>> read(Long userId) {

       List<Order> orders = orderRepository.findAllByUserId(userId);

        return ApiResult.success(orderMapper.toDto(orders));
    }

    @Override
    public ApiResult<OrderDTO> readOne(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> RestException.notFound("Order with id " + orderId));

        return ApiResult.success(orderMapper.toDto(order));
    }

    @Override
    public ApiResult<List<OrderHistoryDTO>> ordersHistory(Long userId) {

        List<Order> orders = orderRepository.findAllByUserId(userId);

        List<OrderHistoryDTO> orderHistoryDTOS = mapToOrderHistory(orders);

        return ApiResult.success(orderHistoryDTOS);
    }


    private static List<OrderProduct> mapToOrderProduct(List<BasketProduct> products) {

        List<OrderProduct> orderProducts = new ArrayList<>();

        for (BasketProduct product : products) {

            OrderProduct orderProduct = new OrderProduct(
                    product.getProduct(),
                    product.getProduct().getName(),
                    product.getProduct().getPrice(),
                    product.getQuantity()
            );
            orderProducts.add(orderProduct);
        }

        return orderProducts;
    }

    private List<OrderHistoryDTO> mapToOrderHistory(List<Order> orders) {

        List<OrderHistoryDTO> orderHistoryDTOS = new ArrayList<>();

        for (Order order : orders) {
            OrderHistoryDTO orderHistoryDTO = new OrderHistoryDTO(
                   order.getId(),
                   order.getOrderDate(),
                   order.getTotalPrice()
            );

            orderHistoryDTOS.add(orderHistoryDTO);
        }

      return orderHistoryDTOS;
    }

}
