package com.cts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.entities.Customer;
import com.cts.entities.Employee;
import com.cts.repository.CustomerRepository;
import com.cts.repository.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	// THIS CLASS BELONGS TO SECURITY
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByEmail(username).orElse(null);
		if (customer != null) {
			return new CustomDetails(customer);
		}
		Employee employee = employeeRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
		return new CustomDetails(employee);
	}
}
