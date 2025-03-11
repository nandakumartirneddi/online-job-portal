package com.portal.service;

import com.portal.entity.userentity;
import com.portal.repository.RepositoryUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl  implements UserDetailsService{
    private final RepositoryUser userRepository;

    public UserDetailsServiceImpl(RepositoryUser userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        userentity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword()) //  Password is already hashed
                .roles(user.getRole().toString()) //  Assign user roles
                .build();
    }
    
}
