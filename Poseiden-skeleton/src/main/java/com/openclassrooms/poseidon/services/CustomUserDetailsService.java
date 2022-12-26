package com.openclassrooms.poseidon.services;

import com.openclassrooms.poseidon.models.UserModel;
import com.openclassrooms.poseidon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /*@Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUserName(userName);

        if (user == null) {
            logger.error("User not found: {}", userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        logger.info("Found User: {}", user);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        //UserDetails userDetails = new User(user.getUsername(),
                //user.getPassword(), Arrays.asList(authority));
        return new User(user.getUsername(),
                user.getPassword(), Collections.singletonList(authority));
    }*/

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(userName);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        //UserDetails userDetails = (UserDetails)new org.springframework.security.core.userdetails.User(user.getUsername(),
                //user.getPassword(), Arrays.asList(authority));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), Collections.singletonList(authority));
    }

    /*@Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        //UserModel personToLogin = userService.getUserByEmail(s);
        UserModel personToLogin = userRepository.findByUserName(s);
        if(personToLogin.getRole().equals("ADMIN")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
            return new User(personToLogin.getUsername(), personToLogin.getPassword(), grantedAuthorities);
        }
        else {
            grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
            return new User(personToLogin.getUsername(), personToLogin.getPassword(), grantedAuthorities);
        }
    }*/
}