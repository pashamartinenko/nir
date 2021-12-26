package com.diploma.factory;

import com.diploma.config.Configuration;
import com.diploma.config.JavaConfiguration;
import com.diploma.configurator.BeanConfigurator;
import com.diploma.configurator.JavaBeanConfigurator;
import lombok.SneakyThrows;

public class BeanFactory {

    private static final BeanFactory BEAN_FACTORY = new BeanFactory();
    private final BeanConfigurator beanConfigurator;
    private final Configuration configuration;

    public BeanFactory() {
        this.configuration = new JavaConfiguration();
        this.beanConfigurator = new JavaBeanConfigurator(configuration.getPackageToScan());
    }

    public static BeanFactory getInstance() {
        return BEAN_FACTORY;
    }

    @SneakyThrows
    public <T> T getBean(Class<T> clazz) {
        Class<? extends T> implementationClass = clazz;
        if(clazz.isInterface()) {
            implementationClass =  beanConfigurator.getImplementationClass(clazz);
        }

        return implementationClass.getDeclaredConstructor().newInstance();
    }
}
