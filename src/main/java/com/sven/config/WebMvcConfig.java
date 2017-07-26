package com.sven.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sven.interceptor.BreadCrumbInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter
{
    
    @Autowired
    private BreadCrumbInterceptor breadCrumbInterceptor;
    @Override
    public void addInterceptors(final InterceptorRegistry registry)
    {
        registry.addInterceptor(breadCrumbInterceptor);
    }

}
