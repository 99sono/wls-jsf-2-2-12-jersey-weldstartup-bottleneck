# In WLS - Jersey Http - is during the WELD#deployBeans() phase - a costly observer of the process annotated types event

Jersey seems to be the main culprit for the  2/3 of the WelldBostrap phase time due to its isJaxRsComponentType - jersey code should be tuned to reduce its footprint


PROBLEM 1:

```
 at java.lang.Throwable.fillInStackTrace(Native Method) 
at java.lang.Throwable.fillInStackTrace(Throwable.java:783) 
- locked [0x00000000e41feb68] (a java.lang.NoSuchMethodException) 
at java.lang.Throwable. (Throwable.java:265) 
at java.lang.Exception. (Exception.java:66) 
at java.lang.ReflectiveOperationException. (ReflectiveOperationException.java:56) 
at java.lang.NoSuchMethodException. (NoSuchMethodException.java:51) 
at java.lang.Class.getMethod(Class.java:1786) 
at org.glassfish.jersey.internal.util.ReflectionHelper$18.run(ReflectionHelper.java:1342) 
at org.glassfish.jersey.internal.util.ReflectionHelper$18.run(ReflectionHelper.java:1338) 
at java.security.AccessController.doPrivileged(Native Method) 
at org.glassfish.jersey.server.model.AnnotatedMethod.findAnnotatedMethod(AnnotatedMethod.java:300) 
at org.glassfish.jersey.server.model.AnnotatedMethod.findAnnotatedMethod(AnnotatedMethod.java:319) 
at org.glassfish.jersey.server.model.AnnotatedMethod.findAnnotatedMethod(AnnotatedMethod.java:291) 
at org.glassfish.jersey.server.model.AnnotatedMethod. (AnnotatedMethod.java:116) 
at org.glassfish.jersey.server.model.MethodList. (MethodList.java:124) 
at org.glassfish.jersey.server.model.IntrospectionModeller.checkForNonPublicMethodIssues(IntrospectionModeller.java:172) 
at org.glassfish.jersey.server.model.IntrospectionModeller.doCreateResourceBuilder(IntrospectionModeller.java:119) 
at org.glassfish.jersey.server.model.IntrospectionModeller.access$000(IntrospectionModeller.java:80) 
at org.glassfish.jersey.server.model.IntrospectionModeller$1.call(IntrospectionModeller.java:112) 
at org.glassfish.jersey.server.model.IntrospectionModeller$1.call(IntrospectionModeller.java:109) 
at org.glassfish.jersey.internal.Errors.process(Errors.java:315) 
at org.glassfish.jersey.internal.Errors.process(Errors.java:297) 
at org.glassfish.jersey.internal.Errors.processWithException(Errors.java:255) 
at org.glassfish.jersey.server.model.IntrospectionModeller.createResourceBuilder(IntrospectionModeller.java:109) 
at org.glassfish.jersey.server.model.Resource.from(Resource.java:797) 
at org.glassfish.jersey.server.model.Resource.from(Resource.java:784) 
at org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$3.compute(CdiComponentProvider.java:178) 
at org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$3.compute(CdiComponentProvider.java:175) 
at org.glassfish.hk2.utilities.cache.Cache$OriginThreadAwareFuture$1.call(Cache.java:97) 
at java.util.concurrent.FutureTask.run(FutureTask.java:266) 
at org.glassfish.hk2.utilities.cache.Cache$OriginThreadAwareFuture.run(Cache.java:154) 
at org.glassfish.hk2.utilities.cache.Cache.compute(Cache.java:199) 
at org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$2.compute(CdiComponentProvider.java:171) 
at org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$2.compute(CdiComponentProvider.java:166) 
at org.glassfish.hk2.utilities.cache.Cache$OriginThreadAwareFuture$1.call(Cache.java:97) 
at java.util.concurrent.FutureTask.run(FutureTask.java:266) 
at org.glassfish.hk2.utilities.cache.Cache$OriginThreadAwareFuture.run(Cache.java:154) 
at org.glassfish.hk2.utilities.cache.Cache.compute(Cache.java:199) 
at org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider.isJaxRsComponentType(CdiComponentProvider.java:822) 
at org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider.processInjectionTarget(CdiComponentProvider.java:668) 
-------------------------------------
-- And Here we Jersey getting involved with the Weld deployment
-----------------------------------

at sun.reflect.GeneratedMethodAccessor433.invoke(Unknown Source) 
at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) 
at java.lang.reflect.Method.invoke(Method.java:498) 
at org.jboss.weld.injection.StaticMethodInjectionPoint.invoke(StaticMethodInjectionPoint.java:88) 
at org.jboss.weld.injection.StaticMethodInjectionPoint.invoke(StaticMethodInjectionPoint.java:78) 
at org.jboss.weld.injection.MethodInvocationStrategy$SimpleMethodInvocationStrategy.invoke(MethodInvocationStrategy.java:129) 
at org.jboss.weld.event.ObserverMethodImpl.sendEvent(ObserverMethodImpl.java:309) 
at org.jboss.weld.event.ExtensionObserverMethodImpl.sendEvent(ExtensionObserverMethodImpl.java:124) 
- locked [0x00000000bdd851b8] (a org.jboss.weld.Container) 
at org.jboss.weld.event.ObserverMethodImpl.sendEvent(ObserverMethodImpl.java:287) 
at org.jboss.weld.event.ObserverMethodImpl.notify(ObserverMethodImpl.java:265) 
at org.jboss.weld.event.ObserverNotifier.notifySyncObservers(ObserverNotifier.java:271) 
at org.jboss.weld.event.ObserverNotifier.notify(ObserverNotifier.java:260) 
at org.jboss.weld.event.ObserverNotifier.fireEvent(ObserverNotifier.java:154) 
at org.jboss.weld.event.ObserverNotifier.fireEvent(ObserverNotifier.java:148) 
at org.jboss.weld.bootstrap.events.AbstractContainerEvent.fire(AbstractContainerEvent.java:53) 
at org.jboss.weld.bootstrap.events.AbstractDefinitionContainerEvent.fire(AbstractDefinitionContainerEvent.java:42) 
at org.jboss.weld.bootstrap.events.AbstractProcessInjectionTarget.fire(AbstractProcessInjectionTarget.java:33) 
at org.jboss.weld.bootstrap.events.ContainerLifecycleEvents.fireProcessInjectionTarget(ContainerLifecycleEvents.java:249) 
at org.jboss.weld.bootstrap.AbstractBeanDeployer.fireBeanEvents(AbstractBeanDeployer.java:135) 
at org.jboss.weld.bootstrap.AbstractBeanDeployer.fireBeanEvents(AbstractBeanDeployer.java:125) 



at org.jboss.weld.bootstrap.BeanDeployer.deploy(BeanDeployer.java:325) 
at org.jboss.weld.bootstrap.BeanDeployment.deployBeans(BeanDeployment.java:267) 
at org.jboss.weld.bootstrap.WeldStartup.deployBeans(WeldStartup.java:415) 
at org.jboss.weld.bootstrap.WeldBootstrap.deployBeans(WeldBootstrap.java:83) 
- locked [0x00000000bdcd44e8] (a org.jboss.weld.bootstrap.WeldBootstrap) 
at com.oracle.injection.provider.weld.WeldInjectionContainer.deploy(WeldInjectionContainer.java:143) 
at com.oracle.injection.integration.CDIAppDeploymentExtension.initCdi(CDIAppDeploymentExtension.java:82) 
at com.oracle.injection.integration.CDIAppDeploymentExtension.activate(CDIAppDeploymentExtension.java:43)
```
		
PROBLEM 2
In WLS, by default, the CDI Bean class scanning that takes place as the first step of WeldBoostrap is happening in a single-threaded approach. However, weld supports a multi-threaded class scanning approach that should be preferred. 

Work-around:
-Dorg.jboss.weld.bootstrap.concurrentDeployment=true

This system property will override the default behavior and tell Weld that it should the multi-threaded ConcurrentDeployer approach.
On expensinve Weld initilizations (e.g. 6 seconds of class aquisition time, this tunning can reduce the WeldBoostrap time by 2 seconds).
On this sample applicaiton, the class scanning is barely felt. The only hase tracked when sampling the application eveyr second is the deployBeans() phase because of the weld observer however.
