package uz.pdp.app_spring_boot_fastfood_online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_spring_boot_fastfood_online.entity.Filial;

import java.util.Optional;

public interface FilialRepository extends JpaRepository<Filial, Long> {

    Optional<Filial> findByContactNumber(String contactNumber);

}



