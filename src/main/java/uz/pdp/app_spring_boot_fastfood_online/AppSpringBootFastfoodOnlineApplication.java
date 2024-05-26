package uz.pdp.app_spring_boot_fastfood_online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class AppSpringBootFastfoodOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppSpringBootFastfoodOnlineApplication.class, args);
    }

//    TODO Stock scheduling
//    todo Basket, business logics
//Jasur

}
