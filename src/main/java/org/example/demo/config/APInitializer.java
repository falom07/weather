package org.example.demo.config;

import jakarta.annotation.Nullable;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class APInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class, DBConfig.class};//for root context
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebAppConfig.class};   //for servlet context,where are dispatcher servlet
    }

    @Override
    @NonNull // because in parent class @NonNullAPi
    protected String[] getServletMappings() {
        return new String[]{"/"};   //write pattern that will be accept our url. if we wrote *.some -> then we could handle only url with end .some
    }
}
