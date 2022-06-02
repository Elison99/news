package org.example.article.service;

import org.example.model.article.dtos.CollectionBehaviorDto;
import org.example.model.common.dtos.ResponseResult;

public interface ApCollectionService {
    /**
     * 用户收藏
     * @param dto
     * @return
     */
    ResponseResult collection(CollectionBehaviorDto dto);
}
