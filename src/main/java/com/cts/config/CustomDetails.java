package com.cts.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cts.entities.Customer;
import com.cts.entities.Employee;

public class CustomDetails implements UserDetails {
	
	// THIS CLASS BELONGS TO SECURITY
 
//	THIS MEANS THAT THIS THE CURRENT USER (BECAUSE WE IMPLEMENTS "UserDetails" HERE)
	
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public CustomDetails(Object user) {
		if (user instanceof Customer) {
			Customer customer = (Customer) user;
			this.username = customer.getEmail();
			this.password = customer.getPassword();
			this.authorities = Collections.singleton(new SimpleGrantedAuthority(customer.getRole()));
		} else if (user instanceof Employee) {
			Employee employee = (Employee) user;
			this.username = employee.getEmail();
			this.password = employee.getPassword();
			this.authorities = Collections.singleton(new SimpleGrantedAuthority(employee.getRole()));
		}
	}

	@Override public Collection<? extends GrantedAuthority> getAuthorities() { 
		return authorities;
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
}