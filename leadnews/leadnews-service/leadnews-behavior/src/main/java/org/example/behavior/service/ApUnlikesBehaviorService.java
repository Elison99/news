package org.example.behavior.service;

import org.example.model.behavior.dtos.UnLikesBehaviorDto;
import org.example.model.common.dtos.ResponseResult;

public interface ApUnlikesBehaviorService {

    ResponseResult unLike(UnLikesBehaviorDto dto);
}
