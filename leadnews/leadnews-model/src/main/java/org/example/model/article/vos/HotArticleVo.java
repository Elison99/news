package org.example.model.article.vos;

import lombok.Data;
import org.example.model.article.pojos.ApArticle;

@Data
public class HotArticleVo extends ApArticle {
    /**
     * 文章分值
     */
    private Integer score;
}
