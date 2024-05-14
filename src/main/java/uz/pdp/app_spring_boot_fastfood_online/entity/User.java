package uz.pdp.app_spring_boot_fastfood_online.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.app_spring_boot_fastfood_online.entity.templates.AbsLongEntity;
import uz.pdp.app_spring_boot_fastfood_online.enums.RegionEnum;
import uz.pdp.app_spring_boot_fastfood_online.enums.RoleEnum;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "users")
public class User extends AbsLongEntity implements UserDetails {

    private String username;

    private String contactNumber;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Enumerated(EnumType.STRING)
    private RegionEnum region;

    private String address;

    private Date birthDate;

    private Boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String Role = "Role_";
        return List.of(new SimpleGrantedAuthority(Role+role.name()));    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
