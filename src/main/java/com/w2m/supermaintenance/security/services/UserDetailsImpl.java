package com.w2m.supermaintenance.security.services;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.w2m.supermaintenance.models.User;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	@JsonIgnore
	private String password;
    private Collection<? extends GrantedAuthority> authorities;
	private String jwtToken;

    public UserDetailsImpl(
        Long id,
        String username,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        String jwtToken
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
		this.jwtToken = jwtToken;
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            Collections.emptyList(),
            null
        );
    }

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public Long getId() {
		return id;
	}

	public String getJwtToken() {
        return jwtToken;
    }
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return username;
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

    @Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
		return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
