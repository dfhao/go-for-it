package com.dfhao.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java Bean转换
 *
 * @author :  dfhao
 * @date :  2021/3/16 22:46
 */
public class BeanUtil {
    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    public static <S, T> T bean2Bean(S source, Class<T> target) throws IllegalAccessException, InstantiationException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        T newT = target.newInstance();
        BeanUtils.copyProperties(source, newT);
        return newT;
    }

    public static <S> Map<String, Object> bean2Map(S source) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Assert.notNull(source, "Source must not be null");
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(source.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            // 过滤class属性
            if (!key.equals("class")) {
                // 得到property对应的getter方法
                Method getter = property.getReadMethod();
                Object value = getter.invoke(source);
                map.put(key, value);
            }
        }
        return map;
    }

    public static Object map2Bean(Class type, Map map) throws InvocationTargetException, IllegalAccessException, InstantiationException, IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        Object obj = type.newInstance();
        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            if (map.containsKey(propertyName)) {
                Object value = map.get(propertyName);
                Object[] args = new Object[1];
                args[0] = value;
                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }

    public static <S, T> List<T> list2List(List<S> sourceList, Class<T> target) throws IllegalAccessException, InstantiationException {
        if (sourceList == null || sourceList.isEmpty()) {
            return null;
        }
        List<T> list = new ArrayList<>(sourceList.size());
        for (S sourceObject : sourceList) {
            if (sourceObject != null) {
                // 目标实体清空值
                T newT = target.newInstance();
                // 实体赋值
                BeanUtils.copyProperties(sourceObject, newT);
                list.add(newT);
            }
        }
        return list;
    }

}
