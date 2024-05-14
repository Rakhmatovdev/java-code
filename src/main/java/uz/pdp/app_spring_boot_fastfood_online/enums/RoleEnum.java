package uz.pdp.app_spring_boot_fastfood_online.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {

    ADMIN,

    MODERATOR,

    USER ;

    @Override
    public String getAuthority() {
        return name();
    }

}
