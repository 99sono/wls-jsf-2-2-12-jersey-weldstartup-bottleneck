package weldstartup.nondynamicclasses;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionSynchronizationRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Just a CDI bean that is not created dynamically
 */
@Named("requestScopedNonDynamicBean")
@RequestScoped
public class RequestScopedNonDynamicBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestScopedNonDynamicBean.class);

    @Inject
    BeanManager beanManager;
    @Resource
    TransactionSynchronizationRegistry tsr;

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("Post construct method invoked");
    }

    @PreDestroy
    public void preDestroy() {
        LOGGER.info("Pre-destroy method invoked");
    }

    public void dummyLogic() {
        LOGGER.info("Dummy logic invoked");
    }

}
