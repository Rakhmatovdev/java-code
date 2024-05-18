package uz.pdp.app_spring_boot_fastfood_online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_spring_boot_fastfood_online.entity.BasketProduct;

import java.util.List;
import java.util.Optional;

public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
    List<BasketProduct> findAllByBasketId(Long basketId);

    Optional<BasketProduct> findByProductId(Long productId);

    void deleteAllByBasketId(Long basketId);
}
