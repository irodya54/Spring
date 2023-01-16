package ru.radion.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import ru.radion.annotacions.InjectBeant;
import ru.radion.database.connectionPool.ConnectionPool;

import java.util.Arrays;
@Component
public class InjectBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware, PriorityOrdered {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(bean.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(InjectBeant.class))
                .forEach(field -> {
                            Object pool = context.getBean("pool1", ConnectionPool.class);
                            ReflectionUtils.makeAccessible(field);
                            ReflectionUtils.setField(field, bean, pool);
                        }
                );
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE;
    }
}
