package com.CloudCash.reward_service.controller;


import com.CloudCash.reward_service.entity.Reward;
import com.CloudCash.reward_service.repository.RewardRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reward")
public class RewardController {

    private final RewardRepository rewardRepository;


    public RewardController(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    @GetMapping("/all")
    public List<Reward> getAllRewards(){
        return rewardRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public  List<Reward> getRewardByUserId(@PathVariable Long userID){
        return rewardRepository.findByUserId(userID);
    }
}
