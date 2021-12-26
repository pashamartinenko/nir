package com.diploma.configurator;

import org.reflections.Reflections;

import java.util.Set;

public class JavaBeanConfigurator implements BeanConfigurator {

    final Reflections scanner;

    public JavaBeanConfigurator(String packageToScan) {

        this.scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass) {
        Set<Class<? extends T>> implementationClasses = scanner.getSubTypesOf(interfaceClass);
        if(implementationClasses.size() != 1) {
            throw new RuntimeException("Interface has more than one implementations!");
        }
        return implementationClasses.stream().findFirst().get();
    }
}
