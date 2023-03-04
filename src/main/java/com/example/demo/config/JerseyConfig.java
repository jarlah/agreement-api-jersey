package com.example.demo.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class JerseyConfig {

    public static ResourceConfig getResourceConfig(AbstractBinder binder) {
        final var rc = new ResourceConfig().packages("com.example.demo");
        rc.register(binder);
        rc.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        return rc;
    }
}
