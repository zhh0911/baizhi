package com.baizhi.kafkaregister.kafka;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Map;
@Component
public class ObjectCodec implements Serializer, Deserializer {
    @Override
    public Object deserialize(String s, byte[] bytes) {
        return SerializationUtils.deserialize(bytes);
    }

    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Object o) {
        return SerializationUtils.serialize(o);
    }

    @Override
    public void close() {

    }
}
