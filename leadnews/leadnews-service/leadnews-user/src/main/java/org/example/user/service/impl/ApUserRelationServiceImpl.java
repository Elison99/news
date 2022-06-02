package org.example.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.common.constants.BehaviorConstants;
import org.example.common.redis.CacheService;
import org.example.model.common.dtos.ResponseResult;
import org.example.model.common.enums.AppHttpCodeEnum;
import org.example.model.user.dtos.UserRelationDto;
import org.example.model.user.pojos.ApUser;
import org.example.user.service.ApUserRelationService;
import org.example.utils.thread.AppThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApUserRelationServiceImpl implements ApUserRelationService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CacheService cacheService;

    /**
     * 用户关注功能
     * @param dto
     * @return
     */
    @Override
    public ResponseResult follow(UserRelationDto dto) {
        //参数检验
        if (dto == null || dto.getOperation() == null || dto.getOperation()<0 || dto.getOperation()>1){
            return  ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //判断是否登录
        ApUser user = AppThreadLocalUtil.getUser();
        if (user == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        Integer apUserId = user.getId();
        //3 关注 apuser:follow:  apuser:fans:
        Integer authorId = dto.getAuthorId();
        if (dto.getOperation() == 0){
            //将对方写入我的关注中
            //zset可以用来存储粉丝列表，value值是粉丝的用户ID，score是关注时间，便可以对粉丝列表按关注时间进行排序。
            cacheService.zAdd(BehaviorConstants.APUSER_FOLLOW_RELATION+ apUserId,authorId.toString(),System.currentTimeMillis());
            //将我写入对方粉丝中
            cacheService.zAdd(BehaviorConstants.APUSER_FANS_RELATION+authorId,apUserId.toString(),System.currentTimeMillis());
        }else{
            //取消关注
            cacheService.zRemove(BehaviorConstants.APUSER_FOLLOW_RELATION+authorId,apUserId.toString());
            cacheService.zRemove(BehaviorConstants.APUSER_FANS_RELATION+authorId,apUserId.toString());
        }

        return ResponseResult.errorResult(AppHttpCodeEnum.SUCCESS);
    }
}
