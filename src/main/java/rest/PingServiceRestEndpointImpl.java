package rest;

import java.time.Instant;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weldstartup.nondynamicclasses.AppScopedNonDynamicBean;

/**
 * A rest endpoint that just responds to a ping request.
 *
 */
@Path(PingServiceRestEndpointImpl.REST_SERVICE_CONTEXT)
@Consumes({ "application/json", "text/html" })
@Produces({ "application/json" })
@ApplicationScoped
public class PingServiceRestEndpointImpl implements PingServiceRestEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingServiceRestEndpointImpl.class);

    /**
     * All services of this rest service have this path part of the URL.
     */
    public static final String REST_SERVICE_CONTEXT = "/pingService";

    @Inject
    AppScopedNonDynamicBean appScopedNonDynamicBean;

    /**
     * Post construct logic. Simply notifies on the server log that the rest endpoint is ready to start serving requets.
     */
    @PostConstruct
    public void postConstruct() {
        LOGGER.info("Rest Endpoint constructed.");
    }

    @Override
    @GET
    @Path("/ping")
    public String ping() {
        Instant date = Instant.now();
        return date.toString();
    }

}
