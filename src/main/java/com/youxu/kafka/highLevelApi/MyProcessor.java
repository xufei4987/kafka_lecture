package com.youxu.kafka.highLevelApi;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;


/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/8/8 19:45
 **/
public class MyProcessor implements Processor<byte[],byte[]> {
    private ProcessorContext context;
    @Override
    public void init(ProcessorContext context) {
        this.context = context;
    }

    @Override
    public void process(byte[] key, byte[] value) {
        String s = new String(value);
        s = s.replaceAll(">>>", " ");
        value = s.getBytes();
        context.forward(key,value);
    }

    @Override
    public void close() {

    }
}
