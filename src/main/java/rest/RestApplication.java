package rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
@ApplicationPath("restServices")
public class RestApplication extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestApplication.class);

    /**
     * Default not arguments constructor.
     */
    public RestApplication() {
        LOGGER.trace("Rest application constructed");
    }

}
