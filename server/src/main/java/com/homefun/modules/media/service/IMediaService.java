package com.homefun.modules.media.service;

import com.homefun.modules.media.domain.MediaCategory;
import com.homefun.modules.media.domain.MediaContent;

import java.util.List;

public interface IMediaService {

    List<MediaCategory> selectCategoryList(MediaCategory category);

    MediaCategory selectCategoryById(Long id);

    void insertCategory(MediaCategory category);

    void updateCategory(MediaCategory category);

    void deleteCategoryById(Long id);

    int deleteCategoryByIds(Long[] ids);

    List<MediaContent> selectContentList(MediaContent content);

    MediaContent selectContentById(Long id);

    void insertContent(MediaContent content);

    void updateContent(MediaContent content);

    void deleteContentById(Long id);

    int deleteContentByIds(Long[] ids);

    List<MediaContent> selectContentByCategoryId(Long categoryId);

    void incrementViewCount(Long id);

    void incrementFavoriteCount(Long id);
}
