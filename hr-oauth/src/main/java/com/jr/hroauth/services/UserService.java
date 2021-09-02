package com.jr.hroauth.services;

import com.jr.hroauth.entities.User;
import com.jr.hroauth.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public User findByEmail(String email) {
        User user = userFeignClient.findByEmail(email).getBody();

        if(user == null){
            logger.error("email not found: " + email);
            throw new IllegalArgumentException("email not found");
        }

        logger.info("email found: " + email);

        return user;
    }

}
