package com.CloudCash.reward_service.kafka;

import com.CloudCash.reward_service.entity.Reward;
import com.CloudCash.reward_service.entity.Transaction;
import com.CloudCash.reward_service.repository.RewardRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RewardConsumer {

    private final RewardRepository rewardRepository;

    public RewardConsumer(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    @KafkaListener(topics = "txn-initiated", groupId = "reward-group")
    public void consumeTransaction(Transaction transaction) {

        if (rewardRepository.existsByTransactionId(transaction.getId())) {
            System.out.println("Reward already exists for transaction " + transaction.getId());
            return;
        }

        Reward reward = new Reward();
        reward.setUserId(transaction.getReceiverId());   // 🎯 reward goes to receiver
        reward.setTransactionId(transaction.getId());
        reward.setPoints(transaction.getAmount() * 100);
        reward.setSentAt(LocalDateTime.now());

        rewardRepository.save(reward);

        System.out.println("Reward created: " + reward.getId());
    }

}
