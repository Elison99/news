package org.example.behavior.service;

import org.example.model.behavior.dtos.LikesBehaviorDto;
import org.example.model.common.dtos.ResponseResult;

public interface ApLikesBehaviorService {


    ResponseResult like(LikesBehaviorDto dto);
}
