package weldstartup.nondynamicclasses;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.transaction.TransactionSynchronizationRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Just a CDI bean that is not created dynamically
 */
@Dependent
public class DependentScopedNonDynamicBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DependentScopedNonDynamicBean.class);

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
