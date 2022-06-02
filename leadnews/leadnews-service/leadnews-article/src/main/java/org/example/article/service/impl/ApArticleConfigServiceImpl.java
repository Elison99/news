package org.example.article.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.article.mapper.ApArticleConfigMapper;
import org.example.article.service.ApArticleConfigService;
import org.example.model.article.pojos.ApArticleConfig;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class ApArticleConfigServiceImpl extends ServiceImpl<ApArticleConfigMapper, ApArticleConfig> implements ApArticleConfigService {
    /**
     * 修改文章配置
     * @param map
     */
    @Override
    public void updateByMap(Map map) {
        //0 下架 1 上架
        Object enable = map.get("enable");
        boolean isDown = true;
        if(enable.equals(1)){
            isDown = false;
        }

        //修改文章配置
        update(Wrappers.<ApArticleConfig>lambdaUpdate()
                .set(ApArticleConfig::getIsDown,isDown)
                .eq(ApArticleConfig::getArticleId,map.get("articleId")));
    }
}
