package com.banking.service;

import com.banking.constants.IKafkaConstants;
import com.banking.model.Account;
import com.banking.producer.ProducerCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class MessageProducer {

    Producer<Long, String> producer = ProducerCreator.createProducer();
    ObjectMapper Obj = new ObjectMapper();
    public void sendMessage(Account account){

        try {
        String jsonobj = Obj.writeValueAsString(account);

        final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(IKafkaConstants.TOPIC_NAME,
                jsonobj);
            RecordMetadata metadata = producer.send(record).get();
            System.out.println("account record added " + metadata.partition()
                    + " with offset " + metadata.offset());
        } catch (ExecutionException e) {
            System.out.println("Error in sending record");
        } catch (InterruptedException e) {
            System.out.println("Error in sending record");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }


}
