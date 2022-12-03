package com.dev.springcloud.ms.auth.service;

import com.dev.springcloud.ms.auth.models.User;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private WebClient.Builder client;

    private Logger log = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = client.build()
                .get()
                .uri("http://ms-users/login", uriBuilder -> uriBuilder.queryParam("email",email).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(User.class)
                .block();
            log.info("User Email: " + user.getEmail());
            log.info("User name: " + user.getNombre());
            log.info("User password: " + user.getPassword());
            return new org.springframework.security.core.userdetails.User(email, user.getPassword(),
                true, true, true, true,
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        } catch (RuntimeException e) {
            String error = "User not found with name: " + email;
            log.error(e.getMessage());
            throw new UsernameNotFoundException(error);
        }

    }
}
