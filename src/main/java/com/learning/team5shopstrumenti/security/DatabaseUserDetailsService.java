package com.learning.team5shopstrumenti.security;

import com.learning.team5shopstrumenti.interfaccie.UserRepository;
import com.learning.team5shopstrumenti.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByEmail(username);
        if(user.isPresent()) {
            return new DatabaseUserDetails(user.get());
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }
}
