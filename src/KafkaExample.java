import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;


//TODO:Имплементирајте ја испитната задача од примерот за RabbitMQ за Kafka како message bus.
//TODO:Значи треба да се реши во KAFKA

//Ima dva tipa na servisi koi se zakacuvaat na sistemot

//Servisi koi baraat mikrovirtuelki. Prakjaat poraka so slednite parametri: tip: storage/compute
//ram: vo megabajti
//broj jadra
//broj sekundi za izvrsuvanje

//Servisi koi nudat mikrovirtuelki
//Treba da gi primat ili storage ili compute porakite i soodvetno da ja izvrsat mikrovirtuelkata?
//(Servisot samo spie onolku sekundi kolku sto e navedeno vo broj na sekundi za izvrsuvanje
//TODO:Потребно е да направите децентрализиран систем за микровиртуелки. Има два типа на
//TODO:сервиси кои ќе се закаќуваат во системот: сервиси кои бараат микровиртуелки и сервиси кои
//TODO:нудат микровиртуелки. Сервисите кои бараат микровиртуелки праќаат порака со следните
//TODO:параметри:
//TODO:- тип: storage/compute
//TODO:- ram: во мегабајти
//TODO:- број јадра
//TODO:- број секунди за извршување

//TODO:Сервисите кои нудат микровиртуелки треба да ги примаат или storage или compute пораките
//TODO:и соодветно да ја извршат микровиртуелката (сервисот само спие онолку секунди колку што е
//TODO:наведено во параметарот број секунди за извршување).

//TODO:Решението треба да користи KAFKA комуникација меѓу сите учесници во протоколот при
//TODO:што може да има паралелни конекции. Треба да смислите протокол за комуникација меѓу
//TODO:сите учесници. Треба да користите соодветни механизми при користење на паралелни
//TODO:Thread-ови доколку е потребно.


//TODO: ispitnata i kolokviumskata vo kafka


//treba racno po klic da filtriras btw
//     ne mozes po group id ili client id
//   Poso producerot gi ignorira
// Badiela stavas vo consumer razl
// Najdovro so contains
// poso ke ti ppfati i student.kancelarijz i dtudent.lab
//  Ako e contains(student)
//  da ne pises 6 ifa


import org.apache.kafka.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class KafkaExample {

    protected static String topic;
    // protected static String group;
    protected static Properties props;

    protected static String brokers;

    public static String getBrokers() {
        return brokers;
    }

    public static Properties getProps() {
        return props;
    }

    public static void setProps(Properties props) {
        KafkaExample.props = props;
    }


    public static Properties createProps(String grupa) {
        String group = grupa;
        String serializer = StringSerializer.class.getName();
        String deserializer = StringDeserializer.class.getName();
        props = new Properties();
        props.put("bootstrap.servers", brokers);//brokers ili bootstrap servers
        props.put("group.id", group + "-consumer");
        //  props.put("group.id",groupCompute+"-consumer");
        //props.put("group.id",groupStorage+"-consumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", deserializer);
        props.put("value.deserializer", deserializer);
        props.put("key.serializer", serializer);
        props.put("value.serializer", serializer);
//        props.put("security.protocol", "SASL_SSL");
//        props.put("sasl.mechanism", "SCRAM-SHA-256");
        //      props.put("sasl.jaas.config", jaasCfg);

        return props;

    }

    public KafkaExample(String brokers, String username, String password) {
        this.topic = "192063";
        //   this.group="group1";
        //    this.groupCompute = "compute";//mi trebat razlicni grupi
        //  this.groupStorage="storage";
        //ama sepak treba da filtriras po kluc


        this.brokers = brokers;
//        String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
//        String jaasCfg = String.format(jaasTemplate, username, password);
/*

        String serializer = StringSerializer.class.getName();
        String deserializer = StringDeserializer.class.getName();
        props = new Properties();
        props.put("bootstrap.servers", brokers);//brokers ili bootstrap servers
        props.put("group.id", group + "-consumer");
      //  props.put("group.id",groupCompute+"-consumer");
        //props.put("group.id",groupStorage+"-consumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", deserializer);
        props.put("value.deserializer", deserializer);
        props.put("key.serializer", serializer);
        props.put("value.serializer", serializer);
//        props.put("security.protocol", "SASL_SSL");
//        props.put("sasl.mechanism", "SCRAM-SHA-256");
        //      props.put("sasl.jaas.config", jaasCfg);

*/

    }


//    public void consume() {
    //      KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
    //    consumer.subscribe(Arrays.asList(topic));
    //  while (true) {
    //    ConsumerRecords<String, String> records = consumer.poll(1000);
    //  for (ConsumerRecord<String, String> record : records) {
    //    System.out.printf("Topic: %s Particija: [%d] offset=%d, key=%s, value=\"%s\"\n",
    //					  record.topic(), record.partition(),
    //					  record.offset(), record.key(), record.value());
    //}
    //}
    //}

    //  public void produce() {
    //    Thread one = new Thread() {
    //      public void run() {
    //        try {
    //          Producer<String, String> producer = new KafkaProducer<>(props);
    //        int i = 0;
    //      while(true) {
    //        Date d = new Date();
    //      producer.send(new ProducerRecord<>(topic, Integer.toString(i), d.toString()));
    //    Thread.sleep(1000);
    //  i++;
    //}
    //   } catch (InterruptedException v) {
    //     System.out.println(v);
    //  }
    //  }
    //  };
    //    one.start();
    // }

    public static void main(String[] args) throws InterruptedException {
//		String brokers = System.getenv("CLOUDKARAFKA_BROKERS");
//		String username = System.getenv("CLOUDKARAFKA_USERNAME");
//		String password = System.getenv("CLOUDKARAFKA_PASSWORD");
        String brokers = "kafkaserver.devops.mk:9092";
        String username = "";
        String password = "";
        KafkaExample c = new KafkaExample(brokers, username, password);
        ServiceZaBaranjeNaMikrovirtuelki serviceZaBaranjeNaMikrovirtuelki = new ServiceZaBaranjeNaMikrovirtuelki();
        serviceZaBaranjeNaMikrovirtuelki.produce();
        ServiceZaNudenjeNaMikrovirtuelki serviceZaNudenjeNaMikrovirtuelki = new ServiceZaNudenjeNaMikrovirtuelki();
        serviceZaNudenjeNaMikrovirtuelki.consume();
        //   serviceZaNudenjeNaMikrovirtuelki.start();

    }
}