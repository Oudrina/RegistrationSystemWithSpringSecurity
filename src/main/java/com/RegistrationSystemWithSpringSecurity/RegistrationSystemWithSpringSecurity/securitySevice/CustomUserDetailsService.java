package com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.securitySevice;

import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.model.User;
import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.model.UserPrincipal;
import com.RegistrationSystemWithSpringSecurity.RegistrationSystemWithSpringSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {

            System.out.println("User not found");

            throw new UsernameNotFoundException("User not found");
        }
            return new UserPrincipal(user);
    }
}
