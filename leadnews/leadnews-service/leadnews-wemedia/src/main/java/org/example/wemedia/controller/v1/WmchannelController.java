package org.example.wemedia.controller.v1;

import org.example.model.common.dtos.ResponseResult;
import org.example.model.wemedia.dtos.ChannelDto;
import org.example.model.wemedia.pojos.WmChannel;
import org.example.wemedia.service.WmChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/channel")
public class WmchannelController {

    @Autowired
    private WmChannelService wmChannelService;

    @GetMapping("/channels")
    public ResponseResult findAll(){
        return wmChannelService.findAll();
    }

    @PostMapping("/list")
    public ResponseResult findByNameAndPage(@RequestBody ChannelDto dto){
        return wmChannelService.findByNameAndPage(dto);
    }

    @PostMapping("save")
    public ResponseResult save(@RequestBody WmChannel adChannel){
        return wmChannelService.insert(adChannel);
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody WmChannel adChannel){
        return wmChannelService.update(adChannel);
    }

    @GetMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id")Integer id){
        return wmChannelService.delete(id);
    }
}