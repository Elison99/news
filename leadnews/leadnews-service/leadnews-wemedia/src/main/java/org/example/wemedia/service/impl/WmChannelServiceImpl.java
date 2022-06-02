package org.example.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.model.common.dtos.PageResponseResult;
import org.example.model.common.dtos.ResponseResult;
import org.example.model.common.enums.AppHttpCodeEnum;
import org.example.model.wemedia.dtos.ChannelDto;
import org.example.model.wemedia.pojos.WmChannel;
import org.example.model.wemedia.pojos.WmNews;
import org.example.wemedia.mapper.WmChannelMapper;
import org.example.wemedia.service.WmChannelService;
import org.example.wemedia.service.WmNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@Slf4j
public class WmChannelServiceImpl extends ServiceImpl<WmChannelMapper, WmChannel> implements WmChannelService {

    @Autowired
    private WmNewsService wmNewsService;

    /**
     * 查询所有频道
     * @return
     */
    @Override
    public ResponseResult findAll() {
        return ResponseResult.okResult(list());
    }

    /**
     * admin端添加
     * @param adChannel
     * @return
     */
    @Override
    public ResponseResult insert(WmChannel adChannel) {

        //检查参数
        if (adChannel == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        WmChannel wmChannel = getOne(Wrappers.<WmChannel>lambdaQuery().eq(WmChannel::getName, adChannel.getName()));

        if (wmChannel != null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST,"频道已经存在");
        }

        //保存
        adChannel.setCreatedTime(new Date());
        save(adChannel);

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * admin端更新
     * @param adChannel
     * @return
     */
    @Override
    public ResponseResult update(WmChannel adChannel) {
        //1.检查参数
        if (adChannel == null || adChannel.getId() == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //判断是否给引用
        int count = wmNewsService.count(Wrappers.<WmNews>lambdaQuery().eq(WmNews::getChannelId,adChannel.getId())
                .eq(WmNews::getStatus,WmNews.Status.PUBLISHED.getCode()));
        if (count > 0){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"频道被引用不能修改");
        }

        //修改
        updateById(adChannel);

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public ResponseResult delete(Integer id) {
        //检查参数
        if (id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //查询平频道
        WmChannel wmChannel = getById(id);
        if (wmChannel == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //频道是否有效
        if (wmChannel.getStatus()){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"频道有效，不能删除");
        }

        //查看是否给引用
        int count = wmNewsService.count(Wrappers.<WmNews>lambdaQuery().eq(WmNews::getChannelId,id)
                .eq(WmNews::getStatus,WmNews.Status.PUBLISHED.getCode()));

        if (count > 0){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"频道被引用不能删除");

        }

        //删除
        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult findByNameAndPage(ChannelDto dto) {
        //检查参数
        if (dto == null){
            return ResponseResult.okResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //检查分页
        dto.checkParam();

        //模糊查询+分页
        Page page = new Page(dto.getPage(), dto.getSize());

        //频道名称模糊查询
        LambdaQueryWrapper<WmChannel> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(dto.getName())){
            lambdaQueryWrapper.like(WmChannel::getName,dto.getName());
        }

        page = page(page,lambdaQueryWrapper);

        PageResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;
    }
}
