package uz.pdp.app_spring_boot_fastfood_online.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.app_spring_boot_fastfood_online.config.AppProperties;
import uz.pdp.app_spring_boot_fastfood_online.entity.User;
import uz.pdp.app_spring_boot_fastfood_online.enums.RegionEnum;
import uz.pdp.app_spring_boot_fastfood_online.enums.RoleEnum;
import uz.pdp.app_spring_boot_fastfood_online.repository.UserRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppProperties properties;


    @Override
    public void run(String... args) throws Exception {

        List<User> admins = userRepository.findByRole(RoleEnum.ADMIN);

        if(!admins.isEmpty())
            return;

        AppProperties.Admin admin = properties.getAdmin();

        String encodedPassword = passwordEncoder.encode(admin.getPassword());

        User user = new User(
                admin.getUsername(),
                admin.getContactNumber(),
                admin.getEmail(),
                encodedPassword,
                RoleEnum.ADMIN,
                RegionEnum.TOSHKENT,
                null,
                null,
                true
        );

        userRepository.save(user);
    }


}
