package uz.pdp.app_spring_boot_fastfood_online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_spring_boot_fastfood_online.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
}