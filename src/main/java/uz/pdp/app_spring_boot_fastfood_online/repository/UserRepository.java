package uz.pdp.app_spring_boot_fastfood_online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_spring_boot_fastfood_online.entity.User;
import uz.pdp.app_spring_boot_fastfood_online.enums.RoleEnum;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByContactNumber(String contactNumber);

    List<User> findByRole(RoleEnum role);



}