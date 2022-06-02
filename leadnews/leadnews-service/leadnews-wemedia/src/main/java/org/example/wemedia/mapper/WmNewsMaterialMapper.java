package org.example.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.wemedia.pojos.WmNews;
import org.example.model.wemedia.pojos.WmNewsMaterial;

import java.util.List;

@Mapper
public interface WmNewsMaterialMapper extends BaseMapper<WmNewsMaterial> {

    public void saveRelations(@Param("materialIds")List<Integer> materialIds,@Param("newsId")
                              Integer newsId,@Param("type")short type);

}
