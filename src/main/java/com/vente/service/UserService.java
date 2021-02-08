package com.vente.service;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vente.entities.CustomUserDetails;
import com.vente.dto.UserRegDto;
import com.vente.entities.Role;
import com.vente.entities.User;
import com.vente.repository.UserRepository;

@Service
public class UserService  implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	
	public User getCurrentlyLoggedInCustomer(Authentication authentication) {
		if(authentication == null) return null;
		
		User user=null;
		Object principal=authentication.getPrincipal();
		
			String email=((CustomUserDetails) principal).getUsername();
            user = userRepository.findByEmail(email);		
		return user;
	}

	public User save(UserRegDto userRegDto) {
		User user = new User(userRegDto.getFirstName(),userRegDto.getLastName(),
				userRegDto.getEmail(),userRegDto.getPassword(),Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user =userRepository.findByEmail(email);
		if(user==null) {
			throw new UsernameNotFoundException("User not found");
			
		}
			return new CustomUserDetails(user);
	}
	
	public void DeleteUser(long id){
		userRepository.deleteById(id);
	}
}
