package org.example.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.model.common.dtos.ResponseResult;
import org.example.model.wemedia.dtos.ChannelDto;
import org.example.model.wemedia.pojos.WmChannel;


public interface WmChannelService extends IService<WmChannel> {

    /**
     * 查询所有频道
     * @return
     */
    public ResponseResult findAll();


    /**
     * admin端新增
     * @param adChannel
     * @return
     */
    ResponseResult insert(WmChannel adChannel);

    /**
     * admin端更新
     * @param adChannel
     * @return
     */
    ResponseResult update(WmChannel adChannel);

    /**
     * admin端删除
     * @param id
     * @return
     */
    ResponseResult delete(Integer id);

    /**
     * admin端查看频道
     * @param dto
     * @return
     */
    ResponseResult findByNameAndPage(ChannelDto dto);
}