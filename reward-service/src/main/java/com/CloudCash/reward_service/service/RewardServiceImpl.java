package com.CloudCash.reward_service.service;

import com.CloudCash.reward_service.entity.Reward;
import com.CloudCash.reward_service.repository.RewardRepository;

import java.time.LocalDateTime;
import java.util.List;

public class RewardServiceImpl implements RewardService{

    public RewardServiceImpl(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    private RewardRepository rewardRepository;

    @Override
    public Reward sendReward(Reward reward) {
        reward.setSentAt(LocalDateTime.now());
        return rewardRepository.save(reward);

    }

    @Override
    public List<Reward> getRewardByUserId(Long UseId) {

        return rewardRepository.findByUserId(UseId);

    }
}
