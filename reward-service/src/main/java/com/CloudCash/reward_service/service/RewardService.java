package com.CloudCash.reward_service.service;

import com.CloudCash.reward_service.entity.Reward;

import java.util.List;

public interface RewardService {

    Reward sendReward(Reward reward);

    List<Reward> getRewardByUserId(Long UseId);
}
