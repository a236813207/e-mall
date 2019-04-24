package com.ken.mall.web.converter;

import com.ken.mall.pojo.base.PairsEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public class StringToPairsEnumConverterFactory implements ConverterFactory<String, Enum> {
    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        Class<?> enumType = targetType;
        while (enumType != null && !enumType.isEnum()) {
            enumType = enumType.getSuperclass();
        }
        if (enumType == null) {
            throw new IllegalArgumentException(
                    "The target type " + targetType.getName() + " does not refer to an enum");
        }
        return new StringToEnum(enumType);
    }


    private class StringToEnum<T extends Enum> implements Converter<String, T> {

        private final Class<T> enumType;

        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            if (source.length() == 0) {
                // It's an empty enum identifier: reset the enum value to null.
                return null;
            }
            if(PairsEnum.class.isAssignableFrom(this.enumType)){
                return convertToPairsEnum(source);
            }
            return (T) Enum.valueOf(this.enumType, source.trim());
        }

        private T convertToPairsEnum(String s){
            if(!s.matches("\\d+")){
                return null;
            }
            int value = Integer.parseInt(s);
            T[] result = enumType.getEnumConstants();
            for(T t:result){
                PairsEnum item = (PairsEnum) t;
                if(item.getValue() == value){
                    return t;
                }
            }
            return null;
        }
    }
}
