package com.api.obra.util;

import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

/* Referencia da função dessa classe utilitaria
 * https://stackoverflow.com/questions/19737626/how-to-ignore-null-values-using-springframework-beanutils-copyproperties
 */
public class NullPropertyNamesUtil {

    private NullPropertyNamesUtil() {}

    public static String[] getNullPropertyNames(Object source) {
        final var wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
}
