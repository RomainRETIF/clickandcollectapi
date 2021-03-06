package com.example.clickandcollectapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import com.example.clickandcollectapi.entities.User;
import com.example.clickandcollectapi.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        

        Optional<User> u = userRepository.findByUsername(s);
        if(u.isPresent()){
            Boolean isAdmin = u.get().getRoles().contains("admin");
            return new org.springframework.security.core.userdetails.User(u.get().getEmail(), u.get().getPassword(), getAuthorities(isAdmin));
        }
        else{
            return null;
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Boolean isAdmin) {
        if(isAdmin){
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else{
            return new ArrayList<>();
        }
        
 }

}