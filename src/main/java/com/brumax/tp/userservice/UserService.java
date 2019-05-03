package com.brumax.tp.userservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserStore userStore;
    private final UserConfig userConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserStore userStore, UserConfig userConfig) {
        this.userStore = userStore;
        this.userConfig = userConfig;
    }

    @Transactional
    public void addUser(User user) {
        this.addUser(user.getLastname(), user.getFirstname());
    }

    @Transactional
    public void addUser(String lastname, String firstname) {
        if (userStore.count() <= userConfig.getMaxUser())
            userStore.save(new User(lastname, firstname));
    }

    @Transactional(readOnly = true)
    public List<User> getUsers(){
        return userStore.findAll();
    }


}
