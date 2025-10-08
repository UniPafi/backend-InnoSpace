package com.innospace.platform.iam.infrastructure.authorization.sfs.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.innospace.platform.iam.domain.model.aggregates.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

    private final String email;
    @JsonIgnore
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public static UserDetailsImpl build(User user) {
        var authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        return new UserDetailsImpl(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public String getUsername() {
        return email;
    }
}
