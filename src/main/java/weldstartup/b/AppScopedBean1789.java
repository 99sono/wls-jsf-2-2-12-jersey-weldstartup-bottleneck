package weldstartup.b;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.transaction.TransactionSynchronizationRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weldstartup.nondynamicclasses.AppScopedNonDynamicBean;
import weldstartup.nondynamicclasses.DependentScopedNonDynamicBean;
import weldstartup.nondynamicclasses.RequestScopedNonDynamicBean;

/**
 * A dynamically created CDI bean meant to demonstrate meant to demonstrate that the WeldStartup performance on weblogic
 * is really under-performing.
 *
 */
@ApplicationScoped
// appScopedName will be turned into a name like AppScopedBean0001
public class AppScopedBean1789 {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppScopedBean1789.class);

    @Inject
    AppScopedNonDynamicBean appScopedNonDynamicBean;

    @Inject
    DependentScopedNonDynamicBean rependentScopedNonDynamicBean;

    @Inject
    RequestScopedNonDynamicBean requestScopedNonDynamicBean;

    @Inject
    BeanManager beanManager;
    @Resource
    TransactionSynchronizationRegistry tsr;

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("Post construct method invoked. AppScopedBean1789");
    }

    @PreDestroy
    public void preDestroy() {
        LOGGER.info("Pre-destroy method invoked. AppScopedBean1789");
    }

    public void dummyLogic() {
        LOGGER.info("Dummy logic invoked. AppScopedBean1789");
    }

}
