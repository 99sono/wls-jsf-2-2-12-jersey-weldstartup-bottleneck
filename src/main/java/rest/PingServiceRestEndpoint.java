package rest;

/**
 *
 *
 */
public interface PingServiceRestEndpoint {

    /**
     * Access url e.g. (http://localhost:7001/appContext/restServices/pingService/ping).
     *
     * @return A string reply that can be checked to validate that the rest service is working.
     */
    String ping();

}
