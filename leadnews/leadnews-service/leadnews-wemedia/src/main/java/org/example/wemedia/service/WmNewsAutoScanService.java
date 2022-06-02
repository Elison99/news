package org.example.wemedia.service;

import org.example.model.common.dtos.ResponseResult;
import org.example.model.wemedia.pojos.WmNews;

public interface WmNewsAutoScanService {

    /**
     * 自媒体文章审核
     * @param id  自媒体文章id
     */
    public void autoScanWmNews(Integer id);

    /**
     * 创建app端文章数据，并修改自媒体文章
     * @param wmNews
     * @return
     */
    ResponseResult saveAppArticle(WmNews wmNews);
}