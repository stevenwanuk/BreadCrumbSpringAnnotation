package com.sven.config;

import java.lang.reflect.Method;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import com.sven.annotation.BreadCrumb;
import com.sven.service.BreadCrumbService;

@Configuration
public class BreadCrumbScanner implements ApplicationContextAware
{

    @Autowired
    private BreadCrumbService breadCrumbService;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext)
            throws BeansException
    {

        for (String beanName : applicationContext.getBeanDefinitionNames())
        {
            Object obj = applicationContext.getBean(beanName);

            Class<?> objClz = obj.getClass();
            if (AopUtils.isAopProxy(obj))
            {

                objClz = org.springframework.aop.support.AopUtils.getTargetClass(obj);
            }

            for (Method m : objClz.getDeclaredMethods())
            {
                if (m.isAnnotationPresent(BreadCrumb.class))
                {
                    breadCrumbService.addRegisteredBreadCrumb(
                            m.getAnnotation(BreadCrumb.class));
                }
            }
        }
    }
}
