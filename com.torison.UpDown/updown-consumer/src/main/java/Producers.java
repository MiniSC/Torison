import org.apache.kafka.clients.producer.*;

import java.util.Properties;


public class Producers {
    public static void main(String[] args){
        Properties props = new Properties();
        props.put("bootstrap.servers","123.207.68.131:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);

            producer.send(new ProducerRecord<String, String>("emailsend","471301240@qq.com"), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if(e != null) {
                        System.out.println("获取消息发送结果");
                    }
                }
            });

        producer.close();

    }
}
