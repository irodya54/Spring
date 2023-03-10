package ru.radion.bpp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import ru.radion.annotacions.Transaction;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
@Component
@Slf4j
public class TransacionBeanPostProcessor implements BeanPostProcessor, Ordered {

    Map<String, Class<?>> beanClasses = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Transaction.class)) {
            beanClasses.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Transaction.class)) {
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        log.warn("Open transaction");
                        try {
                            return method.invoke(bean, args);
                        } finally {
                            log.warn("Close transaction");
                        }
                    });
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
