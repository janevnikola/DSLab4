import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;


class ConsumerModel {
    private String tip; //storage ili compute
    private int ram;
    //broj jadra
    private int brJadra;
    private int sekundiZaIzvrsuvanje;
//broj sekundi za izvrsuvanje
//private tip tip;


    @Override
    public String toString() {
        //TIP:RAM:brJADRA:SEKUNDI
        return tip + ":" + ram + ":" + brJadra + ":" + sekundiZaIzvrsuvanje;
    }

    public ConsumerModel() {
    }

    public ConsumerModel(String tip, int ram, int brJadra, int sekundiZaIzvrsuvanje) {
        this.tip = tip;
        this.ram = ram;
        this.brJadra = brJadra;
        this.sekundiZaIzvrsuvanje = sekundiZaIzvrsuvanje;
    }

    public int generateRam() {
        int min_ram = 1;
        int max_ram = 32;

        //Generate random int value from 50 to 100

        ram = (int) Math.floor(Math.random() * (max_ram - min_ram + 1) + min_ram);
        return ram;
    }

    public int generateJadra() {
        int min_jadra = 1;
        int max_jadra = 32;

        //Generate random int value from 50 to 100

        brJadra = (int) Math.floor(Math.random() * (max_jadra - min_jadra + 1) + min_jadra);
        return brJadra;
    }

    public int generateBrojSekundi() {
        int min_sekundi = 50000;
        int max_sekundi = 100000;

        //Generate random int value from 50 to 100

        sekundiZaIzvrsuvanje = (int) Math.floor(Math.random() * (max_sekundi - min_sekundi + 1) + min_sekundi);
        return sekundiZaIzvrsuvanje;
    }


    // tip: storage/compute
//ram: vo megabajti
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


}
