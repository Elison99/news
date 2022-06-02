package org.example.kafka.sample;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 生产者
 */
public class ProducerQuickStart {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.kafka的配置信息
        Properties properties = new Properties();
        //kafka的连接地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"182.61.37.158:9092");
        //发送失败，失败的重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG,5);
        //消息key的序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        //消息value的序列化器
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        //ack配置  消息确认机制
        properties.put(ProducerConfig.ACKS_CONFIG,"all");

        //数据压缩
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"lz4");

        //2.生产者对象
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);

        //封装要发送的消息
        /**
         * 第一个参数：topic
         * 第二个参数：消息的key
         * 第三个消息：消息的value
         */
        for (int i = 0;i< 10;i++) {
//            ProducerRecord<String, String> record = new ProducerRecord<>("topic-input", "hello kafka");
            ProducerRecord<String, String> record = new ProducerRecord<>("hot.article.score.topic", "hello kafka");


            //3.发送消息
//        RecordMetadata recordMetadata = producer.send(record).get();
//        System.out.println(recordMetadata.offset());

            //异步发送
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e != null){
                        System.out.println("记录异常信息到日志表中");
                    }
                    System.out.println(recordMetadata.offset());
                }
            });
        }

        //4.关闭消息通道，必须关闭，否则消息发送不成功
        producer.close();
    }

}