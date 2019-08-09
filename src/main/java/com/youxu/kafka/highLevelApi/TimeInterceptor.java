package com.youxu.kafka.highLevelApi;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/8/8 17:38
 **/
public class TimeInterceptor implements ProducerInterceptor<String,String> {

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String newVal = System.currentTimeMillis() + "." + record.value();
        return new ProducerRecord(record.topic(),record.key(),newVal);
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
