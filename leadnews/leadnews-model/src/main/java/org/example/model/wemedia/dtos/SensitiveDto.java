package org.example.model.wemedia.dtos;

import lombok.Data;
import org.example.model.common.dtos.PageRequestDto;

@Data
public class SensitiveDto extends PageRequestDto {

    /**
     * 敏感词名称
     */
    private String name;
}
