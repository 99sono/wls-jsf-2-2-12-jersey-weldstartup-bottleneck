package weldstartup.nondynamicclasses;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.transaction.TransactionSynchronizationRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 */
@ApplicationScoped
// appScopedName will be turned into a name like AppScopedBean0001
public class AppScopedNonDynamicBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppScopedNonDynamicBean.class);

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
