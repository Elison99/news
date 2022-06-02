package org.example.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.model.common.dtos.ResponseResult;
import org.example.model.wemedia.dtos.NewsAuthDto;
import org.example.model.wemedia.dtos.WmNewsDto;
import org.example.model.wemedia.dtos.WmNewsPageReqDto;
import org.example.model.wemedia.pojos.WmNews;

public interface WmNewsService extends IService<WmNews> {

    /**
     * 查询文章
     * @param dto
     * @return
     */
    public ResponseResult findAll(WmNewsPageReqDto dto);

    /**
     *  发布文章或保存草稿
     * @param dto
     * @return
     */
    public ResponseResult submitNews(WmNewsDto dto);

    /**
     * 上下架
     * @param enable
     * @param wmNewsId
     * @return
     */
    /**
     * 文章的上下架
     * @param dto
     * @return
     */
    public ResponseResult downOrUp(WmNewsDto dto);

    /**
     * 审核的文章列表查询
     * @param dto
     * @return
     */
    ResponseResult findList(NewsAuthDto dto);

    /**
     * 查询审核文章详情
     * @param id
     * @return
     */
    ResponseResult findWmNewsVo(Integer id);

    /**
     * 成功
     * @param status 2  审核失败  4 审核成功
     * @param dto
     * @return
     */
    ResponseResult updateStatus(Short status, NewsAuthDto dto);


}
