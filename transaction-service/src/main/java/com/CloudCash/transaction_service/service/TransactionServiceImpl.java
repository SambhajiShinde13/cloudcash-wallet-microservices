package com.CloudCash.transaction_service.service;

import com.CloudCash.transaction_service.entity.Transaction;
import com.CloudCash.transaction_service.kafka.KafkaEventProducer;
import com.CloudCash.transaction_service.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final ObjectMapper objectMapper;

    private final KafkaEventProducer kafkaEventProducer;

    public TransactionServiceImpl(TransactionRepository repository, ObjectMapper objectMapper,KafkaEventProducer kafkaEventProducer) {

        this.repository = repository;
        this.objectMapper = objectMapper;
        this.kafkaEventProducer = kafkaEventProducer;
    }

    @Override
    public Transaction createTransaction(Transaction request) {
        System.out.println("Entered createTransaction()");

        Long senderId = request.getSenderId();
        Long receivedId = request.getReceiverId();
        Double amount = request.getAmount();

        Transaction transaction = new Transaction();
        transaction.setSenderId(senderId);
        transaction.setReceiverId(receivedId);
        transaction.setAmount(amount);
      //  transaction.setTimestamp(LocalDateTime.now());
        transaction.setStatus("SUCCESS");

        System.out.println("Incoming Transaction object:"+transaction);

        Transaction saved = repository.save(transaction);
        System.out.println("saved Transaction from DB:" +saved);



        try{
            String eventPayload = objectMapper.writeValueAsString(saved);
            String key = String.valueOf(saved.getId());
            kafkaEventProducer.sendTransactionEvent(key,eventPayload);
            System.out.println("Kafka message sent");
        }catch(Exception e){
            System.out.println("Failed to send kafka event" +e.getMessage());
        }

        return  saved;


    }

    @Override
    public List<Transaction> getAllTransaction() {
        return  repository.findAll();
    }



}
