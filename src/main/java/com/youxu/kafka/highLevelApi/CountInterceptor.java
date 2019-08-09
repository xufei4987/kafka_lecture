package com.youxu.kafka.highLevelApi;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/8/8 17:42
 **/
public class CountInterceptor implements ProducerInterceptor<String,String> {
    private int successCount;
    private int errorCount;
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if(exception != null){
            errorCount ++;
        }else {
            successCount ++;
        }
    }

    @Override
    public void close() {
        System.out.println("发送成功：" + successCount + "条数据");
        System.out.println("发送失败：" + errorCount + "条数据");
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
