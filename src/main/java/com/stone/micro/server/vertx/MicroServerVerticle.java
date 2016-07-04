package com.stone.micro.server.vertx;

import com.alibaba.fastjson.JSON;
import com.stone.service.ConfigLoader;
import com.stone.service.app.UserService;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Young
 * @date 2016/2/16 0016
 */
@Component
public class MicroServerVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(MicroServerVerticle.class);

    @Autowired
    private ConfigLoader configLoader;

    @Autowired
    private UserService userService;

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.route("/sms").handler(
                req -> {
                    LOGGER.info("Received a http request,enter into sms server");
                    //String sms = req.getBodyAsString();
                    vertx.eventBus().send("sms", req.getBodyAsString(), ar -> {
                        if (ar.succeeded()) {
                            req.response().end("hello world");
                        }
                    });
                });

        router.route("/").handler(
                req -> {
                    LOGGER.info("Received a http request");
                    vertx.eventBus().send("email", req.getBodyAsString(), ar -> {
                        if (ar.succeeded()) {
                            req.response().end(JSON.toJSONString(ar.result().body()));
                        }
                    });
                });


        router.route("/register").handler(
                req -> {
            LOGGER.info("Received a http request");
            vertx.eventBus().send("email", req.getBodyAsString(), ar -> {
                if (ar.succeeded()) {
                    req.response().end(JSON.toJSONString(ar.result().body()));
                }
            });
        });



        vertx.createHttpServer().requestHandler(router::accept).listen(18081);
        LOGGER.info("Started HttpServer(port=18081).");
    }
}
