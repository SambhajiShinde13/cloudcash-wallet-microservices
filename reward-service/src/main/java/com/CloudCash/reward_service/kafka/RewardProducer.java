package com.CloudCash.reward_service.kafka;

import com.CloudCash.reward_service.entity.Reward;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RewardProducer {

    private final KafkaTemplate<String, Reward> kafkaTemplate;

    public RewardProducer(KafkaTemplate<String, Reward> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishReward(Reward reward) {
        kafkaTemplate.send("reward-issued", reward.getTransactionId().toString(), reward);
    }
}

