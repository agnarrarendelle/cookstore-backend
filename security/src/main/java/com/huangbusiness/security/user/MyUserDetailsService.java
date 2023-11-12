package com.huangbusiness.security.user;

import com.huangbusiness.repository.entity.UserEntity;
import com.huangbusiness.repository.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserByEmail(email).orElseThrow(()->new UsernameNotFoundException("Not found email: " + email));
        return new MyUserDetails(user.getId(), user.getEmail(), user.getUsername(), user.getPassword(), user.getRoles());
    }
}
