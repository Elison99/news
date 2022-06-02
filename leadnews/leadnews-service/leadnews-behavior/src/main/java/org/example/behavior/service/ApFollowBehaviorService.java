package org.example.behavior.service;


import org.example.model.behavior.dtos.FollowBehaviorDto;

public interface ApFollowBehaviorService {

    /**
     * 保存关注行为
     * @param dto
     * @return
     */
    public boolean saveFollowBehavior(FollowBehaviorDto dto);
}
