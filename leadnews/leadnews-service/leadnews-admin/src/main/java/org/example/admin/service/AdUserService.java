package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.model.admin.dtos.AdUserDto;
import org.example.model.admin.pojos.AdUser;
import org.example.model.common.dtos.ResponseResult;

public interface AdUserService extends IService<AdUser> {

    /**
     * 登录
     * @param dto
     * @return
     */
    public ResponseResult login(AdUserDto dto);
}
