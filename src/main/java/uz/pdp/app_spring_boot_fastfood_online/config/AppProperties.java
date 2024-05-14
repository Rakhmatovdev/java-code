package uz.pdp.app_spring_boot_fastfood_online.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {


    private final Admin admin = new Admin();

    private final Jwt jwt = new Jwt();

    @Getter
    @Setter
    public static class Admin{
        private String username;
        private String contactNumber;
        private String email;
        private String password;
    }

    @Getter
    @Setter
    public static class Jwt{
        private String secretKey;
        private Integer expireDays;
    }

}
