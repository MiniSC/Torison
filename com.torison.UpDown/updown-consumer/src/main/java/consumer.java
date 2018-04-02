import com.sun.jmx.snmp.tasks.ThreadService;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class consumer {
    public static void main(String[] args){
        Properties props = new Properties();
        props.put("bootstrap.servers", "123.207.68.131:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<String,String>(props);
        consumer.subscribe(Arrays.asList("emailsend"),new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            }
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                //将偏移设置到最开始
                consumer.seekToBeginning(collection);
            }
        });
       ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,100,200, TimeUnit.MICROSECONDS, new LinkedBlockingQueue(400),new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolExecutor.execute(
                ()->{
                    System.out.println(Thread.currentThread().getName());
                    while (true) {
                        ConsumerRecords<String, String> records = consumer.poll(100);
                        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
                        for (ConsumerRecord<String, String> record : records) {
                            System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                        }

                    }
                }
        );
        /*threadPoolExecutor.execute(
                ()->{
                    System.out.println(Thread.currentThread().getName());
                    while (true) {
                        ConsumerRecords<String, String> records = consumer.poll(100);
                        for (ConsumerRecord<String, String> record : records) {
                            System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                            System.out.println("t2");
                        }
                    }
                }
        );*/
       /* while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }*/
    }
}
