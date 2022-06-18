package com.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import com.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("**********************loading user datails for  "+username);
		
		com.entity.User user = userRepo.findByuserName(username);
		java.util.List<GrantedAuthority> authority=new ArrayList<GrantedAuthority>();
		
		GrantedAuthority grantAuthority = new UserGrantedAuthority("ROLE_USER");
		authority.add(grantAuthority);
		authority.forEach(auth->{
			System.out.println("authority->"+auth.getAuthority());
		});
		//return new User("user1", "password", authority);
		return new User(user.getUserName(), user.getPassWord(), authority);
		
		

		
	}

}
