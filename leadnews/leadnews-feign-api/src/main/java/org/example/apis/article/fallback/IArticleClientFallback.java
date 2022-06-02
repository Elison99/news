package org.example.apis.article.fallback;

import lombok.extern.slf4j.Slf4j;
import org.example.apis.article.IArticleClient;
import org.example.model.article.dtos.ArticleDto;
import org.example.model.common.dtos.ResponseResult;
import org.example.model.common.enums.AppHttpCodeEnum;
import org.springframework.stereotype.Component;

/**
 * feign的降级配置
 * @author zm
 */
@Component
@Slf4j
public class IArticleClientFallback implements IArticleClient {
    @Override
    public ResponseResult saveArticle(ArticleDto dto) {
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"获取数据失败");
    }
}
