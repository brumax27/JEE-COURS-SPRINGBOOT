package com.brumax.tp.userservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InsertUserJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsertUserJob.class);
    private UserService userService;
    private int counter = 0;


    @Autowired
    public InsertUserJob(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(fixedDelayString = "${app.delay.insert-user-job:2000}")
    public void job(){
        User u = new User("toto-"+ ++counter, "tata");
        userService.addUser(u);
        LOGGER.info("{}", u);
    }
}
