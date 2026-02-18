package com.CloudCash.reward_service.repository;

import com.CloudCash.reward_service.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<Reward,Long> {



        Boolean existsByTransactionId(Long transactionId);

        List<Reward> findByUserId(Long userId);



}
