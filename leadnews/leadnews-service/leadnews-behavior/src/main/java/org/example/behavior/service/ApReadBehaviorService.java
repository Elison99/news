package org.example.behavior.service;

import org.example.model.behavior.dtos.ReadBehaviorDto;
import org.example.model.common.dtos.ResponseResult;

public interface ApReadBehaviorService {

    ResponseResult readBehavior(ReadBehaviorDto dto);
}
