package com.youxu.kafka.highLevelApi;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;

import java.util.Properties;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/8/8 19:26
 **/
public class KfkStreams {
    public static void main(String[] args) {
        Topology topology = new Topology();
        //第一个topic的数据按照规则进行处理后流入第二个topic
        topology.addSource("SOURCE","first")
                .addProcessor("PROCESSOR",MyProcessor::new,"SOURCE")
                .addSink("SINK","second","PROCESSOR");

        Properties properties = new Properties();
        properties.put("application.id","kafkaStream");
        properties.put("bootstrap.servers","10.1.101.217:9092");

        KafkaStreams kafkaStreams = new KafkaStreams(topology,properties);
        kafkaStreams.start();

    }
}
