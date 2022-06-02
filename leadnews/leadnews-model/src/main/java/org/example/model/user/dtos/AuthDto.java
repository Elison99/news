package org.example.model.user.dtos;

import lombok.Data;
import org.example.model.common.dtos.PageRequestDto;

@Data
public class AuthDto  extends PageRequestDto {

    /**
     * 状态
     */
    private Short status;

    private Integer id;

    //驳回的信息
    private String msg;
}
