package org.example.es.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.es.pojo.SearchArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.example.model.article.pojos.ApArticle;

import java.util.List;

@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {

    public List<SearchArticleVo> loadArticleList();

}
