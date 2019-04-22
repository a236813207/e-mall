package com.ken.mall.web.bind.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public enum SerializerFeature {
    WriteNullListAsEmpty,
    WriteNullStringAsEmpty,
    WriteNullNumberAsZero,
    WriteNullBooleanAsFalse;

    public final int mask;

    SerializerFeature() {
        mask = (1 << ordinal());
    }

    final public static class FastJsonSerializerFeatureCompatibleForJackson extends BeanSerializerModifier {
        final private JsonSerializer<Object> nullBooleanJsonSerializer;
        final private JsonSerializer<Object> nullNumberJsonSerializer;
        final private JsonSerializer<Object> nullListJsonSerializer;
        final private JsonSerializer<Object> nullStringJsonSerializer;

        public FastJsonSerializerFeatureCompatibleForJackson(SerializerFeature... features) {
            int config = 0;
            for (SerializerFeature feature : features) {
                config |= feature.mask;
            }
            nullBooleanJsonSerializer = (config & WriteNullBooleanAsFalse.mask) != 0 ? new NullBooleanSerializer() : null;
            nullNumberJsonSerializer = (config & WriteNullNumberAsZero.mask) != 0 ? new NullNumberSerializer() : null;
            nullListJsonSerializer = (config & WriteNullListAsEmpty.mask) != 0 ? new NullListJsonSerializer() : null;
            nullStringJsonSerializer = (config & WriteNullStringAsEmpty.mask) != 0 ? new NullStringSerializer() : null;
        }

        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
            for (BeanPropertyWriter writer : beanProperties) {
                final JavaType javaType = writer.getType();
                final Class<?> rawClass = javaType.getRawClass();
                if (javaType.isArrayType() || javaType.isCollectionLikeType()) {
                    writer.assignNullSerializer(nullListJsonSerializer);
                } else if (Number.class.isAssignableFrom(rawClass) && rawClass.getName().startsWith("java.lang")) {
                    writer.assignNullSerializer(nullNumberJsonSerializer);
                } else if (BigDecimal.class.isAssignableFrom(rawClass)) {
                    writer.assignNullSerializer(nullNumberJsonSerializer);
                } else if (Boolean.class.equals(rawClass)) {
                    writer.assignNullSerializer(nullBooleanJsonSerializer);
                } else if (String.class.equals(rawClass)) {
                    writer.assignNullSerializer(nullStringJsonSerializer);
                }
            }
            return beanProperties;
        }

        private static class NullListJsonSerializer extends JsonSerializer<Object> {
            @Override
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
                jgen.writeStartArray();
                jgen.writeEndArray();
            }
        }

        private static class NullNumberSerializer extends JsonSerializer<Object> {
            @Override
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
                jgen.writeNumber(0);
            }
        }

        private static class NullBooleanSerializer extends JsonSerializer<Object> {
            @Override
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
                jgen.writeBoolean(false);
            }
        }

        private static class NullStringSerializer extends JsonSerializer<Object> {
            @Override
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
                jgen.writeString("");
            }
        }
    }
}
