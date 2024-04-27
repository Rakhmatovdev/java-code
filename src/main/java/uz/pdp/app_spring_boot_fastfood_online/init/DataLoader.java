package uz.pdp.app_spring_boot_fastfood_online.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
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

    private final AppProperties properties;


    @Override
    public void run(String... args) throws Exception {

        List<User> admins = userRepository.findByRole(RoleEnum.ADMIN);

        if(!admins.isEmpty())
            return;

        AppProperties.Admin admin = properties.getAdmin();

        User user = new User(
                admin.getFirstName(),
                admin.getContactNumber(),
                admin.getEmail(),
                admin.getPassword(),
                RoleEnum.ADMIN,
                RegionEnum.TOSHKENT,
                null,
                null,
                true
        );

        userRepository.save(user);

    }


}
