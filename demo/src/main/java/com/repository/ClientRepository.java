package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Client;
import java.lang.String;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer>{
	List<Client> findByClientId(String clientid);
	
	
	

}
