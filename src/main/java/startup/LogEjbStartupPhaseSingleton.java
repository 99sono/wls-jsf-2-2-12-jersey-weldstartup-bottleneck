package startup;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 */
@Startup
@Singleton
@LocalBean
public class LogEjbStartupPhaseSingleton {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogEjbStartupPhaseSingleton.class);

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("\n\n\n\nDEPLOYMENT IS NOW INVOKING STARTUP EJBS. \n\n\n\n\n");
    }

}
