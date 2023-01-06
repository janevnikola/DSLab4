import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import java.util.Date;

class ServiceZaBaranjeNaMikrovirtuelki {//ovie se producers
    private ConsumerModel consumerModel;

    /*    //Servisi koi baraat mikrovirtuelki. Prakjaat poraka so slednite parametri:
        private enum tip {
            storage,
            compute
        }

        // tip: storage/compute
    //ram: vo megabajti
        private int ram;
        //broj jadra
        private int brJadra;
        private int sekundiZaIzvrsuvanje;
    //broj sekundi za izvrsuvanje


        public int getRam() {
            return ram;
        }

        public void setRam(int ram) {
            this.ram = ram;
        }

        public int getBrJadra() {
            return brJadra;
        }

        public void setBrJadra(int brJadra) {
            this.brJadra = brJadra;
        }

        public int getSekundiZaIzvrsuvanje() {
            return sekundiZaIzvrsuvanje;
        }

        public void setSekundiZaIzvrsuvanje(int sekundiZaIzvrsuvanje) {
            this.sekundiZaIzvrsuvanje = sekundiZaIzvrsuvanje;
        }
    */
    public ServiceZaBaranjeNaMikrovirtuelki() {
        consumerModel = new ConsumerModel();
    }

    // public void sendMessage() {
    //   System.out.println();
    //TODO: Сервисите кои бараат микровиртуелки праќаат порака со следните
//TODO:параметри:
//TODO:- тип: storage/compute
//TODO:- ram: во мегабајти
//TODO:- број јадра
//TODO:- број секунди за извршување
    //  System.out.println("Ram: "+ram+"broj jadra: ");

    //  }

    public void produce() {
        Thread one = new Thread() {
            public void run() {
                Integer partition = new Integer(0);
                Long timestamp = new Long(5);

                try {
                    Producer<String, String> producerStorage = new KafkaProducer<>(KafkaExample.createProps("storage"));
                    Producer<String, String> producerCompute = new KafkaProducer<>(KafkaExample.createProps("compute"));

                    int i = 0;
                    while (true) {
                        Date d = new Date();
                        ConsumerModel modelCompute = new ConsumerModel("compute", consumerModel.generateRam(),
                                consumerModel.generateJadra(), consumerModel.generateBrojSekundi());
                        //(String topic, Integer partition, Long timestamp, K key, V value)
                        ConsumerModel modelStorage = new ConsumerModel("storage", consumerModel.generateRam(),
                                consumerModel.generateJadra(), consumerModel.generateBrojSekundi());

                        producerStorage.send(new ProducerRecord<String, String>(KafkaExample.topic,
                                partition, timestamp, "storage", modelStorage.toString()));


                        producerCompute.send(new ProducerRecord<String, String>(KafkaExample.topic,
                                partition, timestamp, "compute", modelCompute.toString()));
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
