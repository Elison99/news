package org.example.behavior.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.behavior.service.ApFollowBehaviorService;
import org.example.model.behavior.dtos.FollowBehaviorDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
@Slf4j
public class ApFollowBehaviorServiceImpl implements ApFollowBehaviorService {

    @Override
    public boolean saveFollowBehavior(FollowBehaviorDto dto) {

        //2.保存关注行为
//        ApFollowBehavior apFollowBehavior = new ApFollowBehavior();
//        apFollowBehavior.setFollowId(dto.getFollowId());
//        apFollowBehavior.setCreatedTime(new Date());
//        apFollowBehavior.setArticleId(dto.getArticleId());
//        apFollowBehavior.setEntryId(apBehaviorEntry.getId());
//        return save(apFollowBehavior);
        return false;
    }
}
