package com.brumax.tp.userservice;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BootListner {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootListner.class);
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public BootListner(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @EventListener //(condition = "#{}") //SPel
    public void on(ApplicationReadyEvent applicationReadyEvent) {
        LOGGER.info("SpringBoot is ready");
        eventPublisher.publishEvent(new BootEndedEvent("Hello from app"));
    }
}

class BootEndedEvent {

    @Getter
    @Setter
    private String message;

    public BootEndedEvent(String message) {
        this.message = message;
    }
}