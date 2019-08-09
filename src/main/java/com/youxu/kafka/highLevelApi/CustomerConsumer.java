package com.youxu.kafka.highLevelApi;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/8/8 16:04
 **/
public class CustomerConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "10.1.101.217:9092");
        props.setProperty("group.id", "test");
        //自动提交offset
        props.setProperty("enable.auto.commit", "true");
        //自动提交延时（1秒后自动提交offset）
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer(props);
        //指定需要消费的Topic
        consumer.subscribe(Arrays.asList("first","second"));
        //消费者拉取数据
        while (true){
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
            consumerRecords.forEach((record)-> System.out.println(record.topic() + "--" + record.partition() + "--" + record.value()));
        }


    }
}
