package org.example.model.behavior.dtos;

import lombok.Data;
import org.example.model.common.annotation.IdEncrypt;

@Data
public class UnLikesBehaviorDto {
    // 文章ID
    @IdEncrypt
    Long articleId;

    /**
     * 不喜欢操作方式
     * 0 不喜欢
     * 1 取消不喜欢
     */
    Short type;

}