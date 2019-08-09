package com.youxu.kafka.highLevelApi;

import org.apache.kafka.clients.producer.*;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/8/7 15:15
 * 为什么需要消息队列：
 * 1、解耦
 * 2、冗余：能够做数据备份
 * 3、扩展性
 * 4、灵活性、峰值处理能力
 * 5、可恢复性
 * 6、顺序保证
 * 7、缓冲
 * 8、异步通信
 * <p>
 * kafka集群和consumer依赖于rookeeper，每一个实例为broker
 * 每个topic都有若干个partition（分区），每个分区都有一个leader和若干个follower，follower为leader的replication（副本），
 * 同一个partition的leader和follower不会在同一个broker中出现。
 * 同一个消费组内的不同消费者不能重复消费同一分区的数据。
 * 老版的kafka的consumer_offset（消费进度）保存在rookeeper中  而新版的kafka为了减少consumer与rookeeper不必要的连接，
 * 将consumer_offset保存在topic中。
 * producer写入kafka的ack应答机制：0（不需要分区leader响应），1（只需要分区leader响应），all（需要leader和所有follower都响应）
 **/
public class CustomerProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.1.101.217:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //自定义分区
//        props.put("partitioner.class","CustomerPartitioner");
        List<String> interceptors = Arrays.asList("com.youxu.kafka.highLevelApi.TimeInterceptor", "com.youxu.kafka.highLevelApi.CountInterceptor");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,interceptors);
        Producer<String, String> producer = new KafkaProducer<>(props);

        for(int i = 0; i < 10; i++){
            producer.send(new ProducerRecord<String, String>("first","hello>>>" + i),
                    ((metadata, exception) -> {
                        if(exception != null){
                            System.out.println("发送失败" + exception.getMessage());
                        }else {
                            System.out.println(metadata.partition() + "--" + metadata.offset());
                        }
                    }));
        }

        producer.close();
    }
}
