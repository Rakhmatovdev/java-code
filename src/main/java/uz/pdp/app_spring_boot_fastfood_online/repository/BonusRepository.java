package uz.pdp.app_spring_boot_fastfood_online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_spring_boot_fastfood_online.entity.Bonus;

import java.util.Optional;

public interface BonusRepository extends JpaRepository<Bonus, Long> {

    Optional<Bonus> findByName(String name);

}
