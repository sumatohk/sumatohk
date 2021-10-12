package com.hkicl.core.model;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
	  String username;
	  String password;
	  Collection<GrantedAuthority> authorities;
}