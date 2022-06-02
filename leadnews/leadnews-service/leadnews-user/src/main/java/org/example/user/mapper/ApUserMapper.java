package org.example.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.model.user.pojos.ApUser;

@Mapper
public interface ApUserMapper extends BaseMapper<ApUser> {
}
