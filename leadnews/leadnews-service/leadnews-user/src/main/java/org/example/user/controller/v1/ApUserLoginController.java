package org.example.user.controller.v1;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.model.common.dtos.ResponseResult;
import org.example.model.user.dtos.LoginDto;
import org.example.user.service.ApUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@Api(value = "app端用户登陆",tags = "app端用户登陆")
public class ApUserLoginController {

    @Autowired
    private ApUserService apUserService;

    @PostMapping("/login_auth")
    @ApiOperation("用户登录")
    public ResponseResult login(@RequestBody LoginDto dto){
        return apUserService.login(dto);
    }
}
