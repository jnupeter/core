package org.jboss.webbeans.porting;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.inject.manager.Manager;

import org.jboss.jsr299.tck.spi.StandaloneContainers;
import org.jboss.webbeans.ManagerImpl;
import org.jboss.webbeans.mock.MockBootstrap;
import org.jboss.webbeans.mock.MockWebBeanDiscovery;

public class StandaloneContainersImpl implements StandaloneContainers
{
   
   public Manager deploy(List<Class<? extends Annotation>> enabledDeploymentTypes, Class<?>... classes)
   {
      MockBootstrap bootstrap = new MockBootstrap();
      ManagerImpl manager = bootstrap.getManager();
      if (enabledDeploymentTypes != null)
      {
         manager.setEnabledDeploymentTypes(enabledDeploymentTypes);
      }
      bootstrap.setWebBeanDiscovery(new MockWebBeanDiscovery(classes));
      bootstrap.boot();
      return manager;
   }
   
   public Manager deploy(java.lang.Class<?>... classes) 
   {
      return deploy(null, classes);
   }
   
}
