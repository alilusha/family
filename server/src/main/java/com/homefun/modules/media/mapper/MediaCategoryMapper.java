package com.homefun.modules.media.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homefun.modules.media.domain.MediaCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MediaCategoryMapper extends BaseMapper<MediaCategory> {

    List<MediaCategory> selectCategoryList(MediaCategory category);

    MediaCategory selectCategoryById(@Param("id") Long id);

    int insertCategory(MediaCategory category);

    int updateCategory(MediaCategory category);

    int deleteCategoryById(@Param("id") Long id);
}
