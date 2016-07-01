package com.stone.micro.server.vertx;

import com.stone.rules.facts.request.Request2Route;
import com.stone.rules.facts.routing.RoutingInfo;
import com.stone.service.app.RoutingPassService;
import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * EmailVerticle
 *
 * @author Young
 * @date 2016/2/16 0016
 */
@Component
public class EmailVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailVerticle.class);

    @Autowired
    private RoutingPassService routingPassService;
    @Override
    public void start() {
        vertx.eventBus().consumer("email", message -> {
                LOGGER.info("Received a message: {}, {}", message.body(), message.headers());
            try {
                Request2Route req=new Request2Route("get","/email");
                RoutingInfo routingInfo= routingPassService.getpayPass(req);
                vertx.createHttpClient().getNow(routingInfo.getPort(), routingInfo.getHost(), routingInfo.getPath(), resp -> {
                    resp.bodyHandler(body -> {
                        message.reply(body+"");
                    });
                });
            } catch (Exception e) {
                LOGGER.error("convert error.", e);
            }
        });

    }
}
