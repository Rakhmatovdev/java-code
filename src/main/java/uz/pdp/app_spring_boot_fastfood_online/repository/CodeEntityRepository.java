package uz.pdp.app_spring_boot_fastfood_online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_spring_boot_fastfood_online.entity.CodeEntity;

import java.util.Optional;

public interface CodeEntityRepository extends JpaRepository<CodeEntity, Long> {
    Optional<CodeEntity> findByEmail(String email);
}