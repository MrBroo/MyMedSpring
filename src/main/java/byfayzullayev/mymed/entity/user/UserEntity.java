package byfayzullayev.mymed.entity.user;

import byfayzullayev.mymed.entity.base.BaseEntity;
import byfayzullayev.mymed.entity.role.RoleEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserEntity extends BaseEntity implements UserDetails {


    private String username;
    private String password;
    private String phoneNumber;
    private boolean active = true;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> roleEntityList;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleEntityList;
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
        return active;
    }
}
