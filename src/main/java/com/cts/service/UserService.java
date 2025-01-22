package com.cts.service;

import com.cts.entities.Customer;

public interface UserService {
    
	public boolean registerUser(Customer user);
	public Customer loginUser(String email, String password); 
	 
}
