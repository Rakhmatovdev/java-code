package uz.pdp.app_spring_boot_fastfood_online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.app_spring_boot_fastfood_online.entity.Basket;
import uz.pdp.app_spring_boot_fastfood_online.entity.User;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    Optional <Basket> findByUserAndIsClosedFalse(User user);
    Optional<Basket> findByUserIdAndIsClosedFalse(Long user_id);

}
