package com.collage.library.model;

import com.collage.library.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "user_tb",uniqueConstraints = @UniqueConstraint(
        name = "ci",
        columnNames = "ci"
))
public class User implements Serializable, UserDetails {

    private static final Long serialVersionUID=1L;

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    @Column(name = "id_user")
    private Long idUser;

    @NotEmpty
    private String ci;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String area;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static User from(UserDto userDto){
        User user=new User();
        user.setCi(userDto.getCi());
        user.setArea(userDto.getArea());
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
        return true;
    }
}
