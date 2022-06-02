package org.example.user.service;


import org.example.model.common.dtos.ResponseResult;
import org.example.model.user.dtos.UserRelationDto;

public interface ApUserRelationService {
    /**
     * 用户关注/取消关注
     * @param dto
     * @return
     */
    public ResponseResult follow(UserRelationDto dto);
}