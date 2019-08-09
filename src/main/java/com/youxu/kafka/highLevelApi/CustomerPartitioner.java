package com.youxu.kafka.highLevelApi;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/8/8 15:48
 * 自定义分区  将数据发送到哪个分区   可以对数据进行分类
 **/
public class CustomerPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
