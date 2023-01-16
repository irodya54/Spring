package ru.radion.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.radion.annotacions.Auditing;
import ru.radion.annotacions.Transaction;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.nanoTime;
@Component
public class AuditingBeanPostProcessor implements BeanPostProcessor {

    Map<String, Class<?>> auditingBean = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Auditing.class)) {
            auditingBean.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = auditingBean.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        System.out.println("Auditing method: " + method.getName());
                        long start = nanoTime();
                        try {
                            return method.invoke(bean, args);
                        } finally {
                            System.out.println("Time: " + (nanoTime() - start)/1000);
                        }
                    });
        }
        return bean;
    }
}
