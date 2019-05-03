package com.brumax.tp.userservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BootEndedListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootEndedListener.class);

    @EventListener
    public void on( BootEndedEvent event){
        LOGGER.info("autre ecouteur appler {}", event.getMessage());
    }

}
