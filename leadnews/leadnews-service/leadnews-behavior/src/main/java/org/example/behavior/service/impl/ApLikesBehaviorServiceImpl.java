package org.example.behavior.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.behavior.service.ApLikesBehaviorService;
import org.example.common.constants.BehaviorConstants;
import org.example.common.constants.HotArticleConstants;
import org.example.common.redis.CacheService;
import org.example.model.behavior.dtos.LikesBehaviorDto;
import org.example.model.common.dtos.ResponseResult;
import org.example.model.common.enums.AppHttpCodeEnum;
import org.example.model.mess.UpdateArticleMess;
import org.example.model.user.pojos.ApUser;
import org.example.utils.thread.AppThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
public class ApLikesBehaviorServiceImpl implements ApLikesBehaviorService {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * 用户点赞行为记录
     * @param dto
     * @return
     */
    @Override
    public ResponseResult like(LikesBehaviorDto dto) {
        //检查参数
        if (dto == null || checkParam(dto)){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //是否登录
        ApUser user = AppThreadLocalUtil.getUser();
        if (user == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        UpdateArticleMess mess = new UpdateArticleMess();
        mess.setArticleId(dto.getArticleId());
        mess.setType(UpdateArticleMess.UpdateArticleType.LIKES);

        //点赞保存数据
        if (dto.getOperation() == 0){
            Object obj = cacheService.hGet(BehaviorConstants.LIKE_BEHAVIOR + dto.getArticleId().toString(), user.getId().toString());
            if (obj != null){
                return  ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST);
            }

            //保存当前key
            log.info("保存当前key：{}，{}，{}",dto.getArticleId(),user.getId(), JSON.toJSONString(dto));
            cacheService.hPut(BehaviorConstants.LIKE_BEHAVIOR+dto.getArticleId(),user.getId().toString(),JSON.toJSONString(dto));
            mess.setAdd(1);
        }else{
            // 删除当前key
            log.info("删除当前key:{}, {}", dto.getArticleId(), user.getId());
            cacheService.hDelete(BehaviorConstants.LIKE_BEHAVIOR + dto.getArticleId().toString(), user.getId().toString());
            mess.setAdd(-1);
        }

        //发送消息，数据聚合
        kafkaTemplate.send(HotArticleConstants.HOT_ARTICLE_SCORE_TOPIC,JSON.toJSONString(mess));

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 检查参数
     * @param dto
     * @return
     */
    private boolean checkParam(LikesBehaviorDto dto) {
        if (dto.getType() > 2 || dto.getType()<0 || dto.getOperation()>1 || dto.getOperation()<0){
            return true;
        }
        return false;
    }

}
