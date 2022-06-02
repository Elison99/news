package org.example.search.service;

import org.example.model.common.dtos.ResponseResult;
import org.example.model.search.dtos.UserSearchDto;

import java.io.IOException;

public interface ArticleSearchService {

    /**
     * ES文章分页搜索
     * @param dto
     * @return
     */
    ResponseResult search(UserSearchDto dto) throws IOException;
}
