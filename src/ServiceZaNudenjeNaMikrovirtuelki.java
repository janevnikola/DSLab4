import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

import java.util.Arrays;

class ServiceZaNudenjeNaMikrovirtuelki extends Thread {//ovie se consumers

    //TODO: Servisi koi nudat mikrovirtuelki
//TODO: Treba da gi primat ili storage ili compute porakite i soodvetno da ja izvrsat mikrovirtuelkata?
//TODO: (Servisot samo spie onolku sekundi kolku sto e navedeno vo broj na sekundi za izvrsuvanje

    //public void run(){//
    // }

    public void consume() throws InterruptedException {

        //brokers se bootstrap servers
        //group e consumer group id

        //Create a consumer
        KafkaConsumer<String, String> consumerCompute = new KafkaConsumer<>(KafkaExample.createProps("compute"));

        KafkaConsumer<String, String> consumerStorage = new KafkaConsumer<>(KafkaExample.createProps("storage"));
        //subscribe na topic
        consumerCompute.subscribe(Collections.singletonList(KafkaExample.topic));
        consumerStorage.subscribe(Collections.singletonList(KafkaExample.topic));
        while (true) {
            ConsumerRecords<String, String> records_compute = consumerCompute.poll(1000);
            ConsumerRecords<String, String> records_storage = consumerStorage.poll(1000);

            for (ConsumerRecord<String, String> record : records_storage) {
                //  Thread.sleep(izvrusvanje);
                if (record.key().equals("storage")) {
                    System.out.printf("Primiv Storage Consumer grupa: Topic: %s Particija: [%d] offset=%d, key=%s, value=\"%s\"\n",
                            record.topic(), record.partition(),
                            record.offset(), record.key(), record.value());
                    //poraka=record.key();
                    String tip;//nemame potreba od proveruvanje bidejki vo ifot imame
                    int ram;
                    int brJadra;
                    int izvrsuvanje;
                    String[] nizaSTR = record.value().split(":");
                    tip = nizaSTR[0];
                    ram = Integer.parseInt(nizaSTR[1]);
                    brJadra = Integer.parseInt(nizaSTR[2]);
                    izvrsuvanje = Integer.parseInt(nizaSTR[3]);


                    Thread.sleep(izvrsuvanje);
                }

            }


            for (ConsumerRecord<String, String> record : records_compute) {

                if (record.key().equals("compute")) {

                    System.out.printf("Dobiv Compute Consumer grupa: Topic: %s Particija: [%d] offset=%d, key=%s, value=\"%s\"\n",
                            record.topic(), record.partition(),
                            record.offset(), record.key(), record.value());
                    String tip;
                    int ram;
                    int brJadra;
                    int izvrsuvanje;
                    String[] nizaSTR = record.value().split(":");
                    tip = nizaSTR[0];
                    ram = Integer.parseInt(nizaSTR[1]);
                    brJadra = Integer.parseInt(nizaSTR[2]);
                    izvrsuvanje = Integer.parseInt(nizaSTR[3]);


                    Thread.sleep(izvrsuvanje);

                }

            }

        }
    }

}
