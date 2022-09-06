package com.murilo.oauth2.services;

import com.murilo.oauth2.entities.User;
import com.murilo.oauth2.shared.getFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private getFeignClient getFeignClient;

    public User findByEmail(String email) {
        User user = getUser(email);
        logger.info("Aluno Carregado" + user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        return user;
    }

    private User getUser(String email) {
        User user = getFeignClient.findByEmail(email).getBody();
        return user;
    }
}
