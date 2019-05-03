package com.brumax.tp.userservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;

public class RateLimiterHandler extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimiterHandler.class);
    private static int countOfCall = 0;
    private static LocalTime timeStart = LocalTime.now();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LocalTime timeEnd = LocalTime.now();
        LOGGER.info("Rate limiter called");
        countOfCall++;
        if (countOfCall < 10 && (timeEnd.toNanoOfDay() - timeStart.toNanoOfDay()) > 10){
            countOfCall = 0;
            return super.preHandle(request, response, handler);
        }

        return false;
    }

}
