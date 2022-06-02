package org.example.model.wemedia.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.example.model.common.dtos.PageRequestDto;

@Data
public class ChannelDto extends PageRequestDto {
    /**
     * 频道名称
     */
    @ApiModelProperty(value = "频道名称")
    private String name;
}
