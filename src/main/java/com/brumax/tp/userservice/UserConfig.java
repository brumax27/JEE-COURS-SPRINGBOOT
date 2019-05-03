package com.brumax.tp.userservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Positive;


@ConfigurationProperties(prefix = "app")
public class UserConfig {

    /**
     * limit max user in app
     */
    @Getter
    @Setter
    private Integer maxUser;
}
