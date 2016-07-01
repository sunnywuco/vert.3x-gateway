package com.stone.micro.server.vertx;

import com.stone.service.SmsService;
import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
/**
 * SmsVerticle
 *
 * @author Young
 * @date 2016/2/16 0016
 */
@Component
public class SmsVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsVerticle.class);
    @Autowired
    private SmsService smsService;
    public void start() {
        vertx.eventBus().consumer("sms", message -> {
            LOGGER.info("Received a message: {}", message.body());
            try {

                    message.reply("ISO-8859-1");

            } catch (Exception e) {
                LOGGER.error("convert error.", e);
            }
        });
    }
}
