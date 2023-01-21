package com.entity;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@jakarta.persistence.Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "password")
	private String passWord;
	@Column(name = "email")
	private String email;
	@Column(name = "is_account_non_expired")
	boolean isAccountNonExpired;
	@Column(name = "is_account_non_locked")
	boolean isAccountNonLocked;
	@Column(name = "is_credentials_non_expired")
	boolean isCredentialsNonExpired;
	@Transient
	Set<GrantedAuthority> authorities;

}
