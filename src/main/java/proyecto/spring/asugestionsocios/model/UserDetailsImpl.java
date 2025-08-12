package proyecto.spring.asugestionsocios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import proyecto.spring.asugestionsocios.model.entity.PermissionState;
import proyecto.spring.asugestionsocios.model.entity.User;
import proyecto.spring.asugestionsocios.model.entity.UserStatus;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    private String profile;
    private List<GrantedAuthority> authorities;
    private boolean isEnabled;

    public UserDetailsImpl(Long id, String email, String password, String profile, List<GrantedAuthority> authorities, boolean isEnabled){
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.profile = profile;
        this.isEnabled = isEnabled;
    }

    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> authority = user.getProfile().getFeatures().stream()
                .filter(fea -> fea.getHasPermissionState() == PermissionState.GRANTED)
                .map(fea -> new SimpleGrantedAuthority(fea.getFeature().getName()))
                .collect(Collectors.toList());

        boolean enabled = user.getStatus() == UserStatus.ACTIVE;

        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getProfile().getName(),
                authority,
                enabled
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return isEnabled;
    }
}
