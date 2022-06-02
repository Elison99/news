package org.example.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.model.article.pojos.ApArticleContent;

@Mapper
public interface ApArticleContentMapper extends BaseMapper<ApArticleContent> {
}