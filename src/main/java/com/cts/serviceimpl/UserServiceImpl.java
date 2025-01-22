package com.cts.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.entities.Customer;
import com.cts.repository.UserRepository;
import com.cts.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
	@Autowired
	private UserRepository userRepository;
	@Override
	public boolean registerUser(Customer user) {
		
		try {
			user.setRole("CUSTOMER");
			userRepository.save(user);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public Customer loginUser(String email, String password) {
		Customer validUser = userRepository.findByEmail(email);
		if(validUser != null && validUser.getPassword().equals(password)) {
			return validUser;
		}
		return null;
	}

	
}
