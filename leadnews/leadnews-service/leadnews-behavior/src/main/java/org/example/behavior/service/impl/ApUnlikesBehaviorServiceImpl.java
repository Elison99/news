package org.example.behavior.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.data.Json;
import org.example.behavior.service.ApUnlikesBehaviorService;
import org.example.common.constants.BehaviorConstants;
import org.example.common.redis.CacheService;
import org.example.model.behavior.dtos.UnLikesBehaviorDto;
import org.example.model.common.dtos.ResponseResult;
import org.example.model.common.enums.AppHttpCodeEnum;
import org.example.model.user.pojos.ApUser;
import org.example.utils.thread.AppThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *     APP不喜欢行为表 服务实现类
 * </p>
 * @author zm
 */
@Slf4j
@Service
public class ApUnlikesBehaviorServiceImpl implements ApUnlikesBehaviorService {

    @Autowired
    private CacheService cacheService;

    /**
     * 文章不喜欢
     * @param dto
     * @return
     */
    @Override
    public ResponseResult unLike(UnLikesBehaviorDto dto) {

        if (dto == null || dto.getArticleId() == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        ApUser user = AppThreadLocalUtil.getUser();
        if (user == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        if (dto.getType() == 0){
            log.info("保存当前key：{}，{}，{}",dto.getArticleId(),dto.getType(), JSON.toJSONString(dto));
            cacheService.hPut(BehaviorConstants.UN_LIKE_BEHAVIOR + dto.getArticleId().toString(), user.getId().toString(), JSON.toJSONString(dto));
        }else{
            log.info("删除当前key：{}，{}，{}",dto.getArticleId(),dto.getType(), JSON.toJSONString(dto));
            cacheService.hDelete(BehaviorConstants.UN_LIKE_BEHAVIOR + dto.getArticleId().toString(), user.getId().toString());
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SUCCESS);
    }
}
