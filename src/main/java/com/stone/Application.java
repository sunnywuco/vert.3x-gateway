package com.stone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stone.micro.server.vertx.SmsVerticle;
import com.stone.micro.server.vertx.EmailVerticle;
import com.stone.micro.server.vertx.MicroServerVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.zookeeper.ZookeeperProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.*;

@SpringBootApplication
@ComponentScan(basePackages = {"com.stone"})
public class Application {

    @Autowired
    protected Environment environment;

    @Value("${spring.cloud.zookeeper.connectString}")
    String zkConnString;

    /**
     * By defining the {@link KieContainer} as a bean here, we ensure that
     * Drools will hunt out the kmodule.xml and rules on application startup.
     * Those can be found in <code>src/main/resources</code>.
     *
     * @return The {@link KieContainer}.
     */
    @Bean
    public KieContainer kieContainer() {
        return KieServices.Factory.get().getKieClasspathContainer();
    }

    @Bean
    public ZookeeperProperties zkProperties() {
        ZookeeperProperties properties = new ZookeeperProperties();
        properties.setConnectString(zkConnString);
        return properties;
    }

    @Bean
    protected ObjectMapper objectMapper() {
        return new ObjectMapper().disable(INDENT_OUTPUT)
                .disable(WRITE_EMPTY_JSON_ARRAYS)
                .disable(WRITE_NULL_MAP_VALUES)
                .disable(FAIL_ON_EMPTY_BEANS)
                .enable(WRITE_DATES_AS_TIMESTAMPS)
                .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .setSerializationInclusion(NON_NULL);
    }

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "true");
        final ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .registerShutdownHook(true)
                .logStartupInfo(true)
                .bannerMode(Banner.Mode.CONSOLE)
                .run(args);

        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(context.getBean(SmsVerticle.class), new DeploymentOptions().setWorker(true));
        vertx.deployVerticle(context.getBean(EmailVerticle.class), new DeploymentOptions().setWorker(true));
        vertx.deployVerticle(context.getBean(MicroServerVerticle.class));
    }
}
