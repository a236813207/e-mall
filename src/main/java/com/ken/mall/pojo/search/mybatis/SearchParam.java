package com.ken.mall.pojo.search.mybatis;


import com.ken.mall.pojo.search.Filter;
import com.ken.mall.pojo.search.mybatis.annotation.Basic;
import com.ken.mall.pojo.search.mybatis.annotation.Like;
import com.ken.mall.pojo.search.mybatis.annotation.Time;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * mybatis查询参数处理
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public abstract class SearchParam {

    public Map<String, Object> buildQueryParams() {
        Map<String, Object> params = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String propertyName = propertyDescriptor.getName();
                if ("class".equals(propertyName)) {
                    continue;
                }
                //map的key
                String key = propertyName;
                Method readMethod = propertyDescriptor.getReadMethod();
                Object value = readMethod.invoke(this);
                if (value == null) {
                    continue;
                }
                KeyValuePairs kv = null;
                try {
                    Field field = this.getClass().getDeclaredField(propertyName);
                    kv = field(field, key, value);
                } catch (NoSuchFieldException ex) {

                }
                //如果field上没有取到就从get方法上取
                if (kv == null) {
                    kv = readMethod(readMethod, key, value);
                }

                if (kv != null) {
                    params.put(kv.getKey(), kv.value);
                } else {
                    params.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }


    private KeyValuePairs field(Field field, String key, Object value) {

        if (field.isAnnotationPresent(Basic.class)) {
            Basic annotation = field.getAnnotation(Basic.class);
            if (annotation.exclude()) {
                return null;
            }
            if (!"".equals(annotation.value().trim())) {
                key = annotation.value();
            }
        }
        if (field.isAnnotationPresent(Time.class)) {
            Time annotation = field.getAnnotation(Time.class);
            TimePattern pattern = annotation.value();
            if (field.getType().isAssignableFrom(Date.class)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime((Date) value);
                if (pattern == TimePattern.start) {
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    value = calendar.getTime();
                } else if (pattern == TimePattern.end) {
                    calendar.set(Calendar.HOUR_OF_DAY, 23);
                    calendar.set(Calendar.MINUTE, 59);
                    calendar.set(Calendar.SECOND, 59);
                    value = calendar.getTime();
                }
            }
        } else if (field.isAnnotationPresent(Like.class) && field.getType().isAssignableFrom(String.class)) {
            Like annotation = field.getAnnotation(Like.class);
            String v = (String) value;
            LikePattern pattern = annotation.pattern();
            switch (pattern) {
                case pre:
                    value = Filter.LikeValue.pre(v);
                    break;
                case post:
                    value = Filter.LikeValue.post(v);
                    break;
                default:
                    value = Filter.LikeValue.both(v);
            }
        }
        return new KeyValuePairs(key, value);
    }

    private KeyValuePairs readMethod(Method readMethod, String key, Object value) {
        if (readMethod.isAnnotationPresent(Basic.class)) {
            Basic annotation = readMethod.getAnnotation(Basic.class);
            if (annotation.exclude()) {
                return null;
            }
            if (!"".equals(annotation.value().trim())) {
                key = annotation.value();
            }
        }
        if (readMethod.isAnnotationPresent(Time.class)) {
            Time annotation = readMethod.getAnnotation(Time.class);
            TimePattern pattern = annotation.value();
            if (readMethod.getReturnType().isAssignableFrom(Date.class)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime((Date) value);
                if (pattern == TimePattern.start) {
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    value = calendar.getTime();
                } else if (pattern == TimePattern.end) {
                    calendar.set(Calendar.HOUR_OF_DAY, 23);
                    calendar.set(Calendar.MINUTE, 59);
                    calendar.set(Calendar.SECOND, 59);
                    value = calendar.getTime();
                }
            }
        } else if (readMethod.isAnnotationPresent(Like.class) && readMethod.getReturnType().isAssignableFrom(String.class)) {
            Like annotation = readMethod.getAnnotation(Like.class);
            String v = (String) value;
            LikePattern pattern = annotation.pattern();
            switch (pattern) {
                case pre:
                    value = Filter.LikeValue.pre(v);
                    break;
                case post:
                    value = Filter.LikeValue.post(v);
                    break;
                default:
                    value = Filter.LikeValue.both(v);
            }
        }
        return new KeyValuePairs(key, value);
    }


    private class KeyValuePairs {
        private String key;
        private Object value;

        public KeyValuePairs(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
