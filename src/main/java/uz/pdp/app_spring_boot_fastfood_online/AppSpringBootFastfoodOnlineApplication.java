package uz.pdp.app_spring_boot_fastfood_online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AppSpringBootFastfoodOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppSpringBootFastfoodOnlineApplication.class, args);
    }

}
