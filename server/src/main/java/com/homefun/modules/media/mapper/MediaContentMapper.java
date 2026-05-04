package com.homefun.modules.media.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homefun.modules.media.domain.MediaContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MediaContentMapper extends BaseMapper<MediaContent> {

    List<MediaContent> selectContentList(MediaContent content);

    MediaContent selectContentById(@Param("id") Long id);

    int insertContent(MediaContent content);

    int updateContent(MediaContent content);

    int deleteContentById(@Param("id") Long id);

    List<MediaContent> selectContentByCategoryId(@Param("categoryId") Long categoryId);

    int incrementViewCount(@Param("id") Long id);

    int incrementFavoriteCount(@Param("id") Long id);
}
