import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Calendar;
import java.util.Date;

public class ProducerModel {
ServiceZaBaranjeNaMikrovirtuelki serviceZaBaranjeNaMikrovirtuelki;

      public void produce() {
        Thread one = new Thread() {
          public void run() {
            try {
              Producer<String, String> producer = new KafkaProducer<>(KafkaExample.props);
            int i = 0;
          while(true) {
            Date d = new Date();
          producer.send(new ProducerRecord<>(KafkaExample.topic, Integer.toString(i), d.toString()));
        Thread.sleep(1000);
      i++;
    }
       } catch (InterruptedException v) {
         System.out.println(v);
      }
      }
      };
        one.start();
     }



}
