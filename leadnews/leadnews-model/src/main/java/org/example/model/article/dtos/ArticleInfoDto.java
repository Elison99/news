package org.example.model.article.dtos;

import lombok.Data;
import org.example.model.common.annotation.IdEncrypt;

@Data
public class ArticleInfoDto {
    // 设备ID
    @IdEncrypt
    Integer equipmentId;
    // 文章ID
    @IdEncrypt
    Long articleId;
    // 作者ID
    @IdEncrypt
    Integer authorId;
}