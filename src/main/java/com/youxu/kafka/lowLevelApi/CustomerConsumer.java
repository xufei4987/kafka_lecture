package com.youxu.kafka.lowLevelApi;

import java.util.Arrays;
import java.util.List;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/8/8 16:28
 * 根据指定的Topic、partition，offset获取数据
 **/
public class CustomerConsumer {
    public static void main(String[] args) {

        List<String> brokers = Arrays.asList("10.1.101.217");
        int port = 9092;
        String topic = "second";
        int partition = 0;
        long offset = 2;


    }

    //找到分区leader
    private String findLeader(List<String> brokers, int port, String topic, int partition){

        return null;
    }

    //获取数据
    private String getDate(){

        return null;
    }
}
