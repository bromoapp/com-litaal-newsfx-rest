package com.litaal.newsfx.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private Boolean enabled;
	private List<GrantedAuthority> authorities;

	public UserInfoDetails(Credential credential, UserInfo user) {
		this.username = credential.getUsername();
		this.password = credential.getPassword();
		this.enabled = credential.getEnabled();
		this.authorities = new ArrayList<>();
		this.authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
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
		return this.enabled;
	}

}
