package org.example.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.model.article.dtos.ArticleDto;
import org.example.model.article.dtos.ArticleHomeDto;
import org.example.model.article.dtos.ArticleInfoDto;
import org.example.model.article.pojos.ApArticle;
import org.example.model.common.dtos.ResponseResult;
import org.example.model.mess.ArticleVisitStreamMess;

public interface ApArticleService extends IService<ApArticle> {

    /**
     * 根据参数加载文章列表
     * @param loadtype 1为加载更多  2为加载最新
     * @param dto
     * @return
     */
    ResponseResult load(Short loadtype, ArticleHomeDto dto);

    /**
     * 加载文章列表
     * @param dto
     * @param type  1 加载更多   2 加载最新
     * @param firstPage  true  是首页  flase 非首页
     * @return
     */
    public ResponseResult load2(ArticleHomeDto dto,Short type,boolean firstPage);

    /**
     * 保存app端的相关文章
     * @param dto
     * @return
     */
    ResponseResult saveArticle(ArticleDto dto);

    /**
     * 加载用户行为
     * @param dto
     * @return
     */
    ResponseResult loadArticleBehavior(ArticleInfoDto dto);

    /**
     * 更新文章的分值，同时更新缓存中的缓存热点文章
     * @param mess
     */
    public void updateScore(ArticleVisitStreamMess mess);
}