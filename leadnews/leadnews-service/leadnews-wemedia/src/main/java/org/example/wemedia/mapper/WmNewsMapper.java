package org.example.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.wemedia.dtos.NewsAuthDto;
import org.example.model.wemedia.pojos.WmNews;
import org.example.model.wemedia.vos.WmNewsVo;

import java.util.List;

@Mapper
public interface WmNewsMapper extends BaseMapper<WmNews> {

    List<WmNewsVo> findListAndPage(@Param("dto") NewsAuthDto dto);

    int findListCount(@Param("dto") NewsAuthDto dto);
}
