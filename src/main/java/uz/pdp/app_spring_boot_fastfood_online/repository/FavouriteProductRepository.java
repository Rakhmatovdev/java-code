package uz.pdp.app_spring_boot_fastfood_online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_spring_boot_fastfood_online.entity.FavouriteProduct;

public interface FavouriteProductRepository extends JpaRepository<FavouriteProduct, Long> {
}