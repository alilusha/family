package com.homefun.modules.media.service.impl;

import com.homefun.modules.media.domain.MediaCategory;
import com.homefun.modules.media.domain.MediaContent;
import com.homefun.modules.media.mapper.MediaCategoryMapper;
import com.homefun.modules.media.mapper.MediaContentMapper;
import com.homefun.modules.media.service.IMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MediaServiceImpl implements IMediaService {

    @Autowired
    private MediaCategoryMapper categoryMapper;

    @Autowired
    private MediaContentMapper contentMapper;

    @Override
    public List<MediaCategory> selectCategoryList(MediaCategory category) {
        return categoryMapper.selectCategoryList(category);
    }

    @Override
    public MediaCategory selectCategoryById(Long id) {
        return categoryMapper.selectCategoryById(id);
    }

    @Override
    @Transactional
    public void insertCategory(MediaCategory category) {
        if (category.getStatus() == null) {
            category.setStatus("0");
        }
        if (category.getSort() == null) {
            category.setSort(0);
        }
        categoryMapper.insertCategory(category);
    }

    @Override
    @Transactional
    public void updateCategory(MediaCategory category) {
        categoryMapper.updateCategory(category);
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        categoryMapper.deleteCategoryById(id);
    }

    @Override
    @Transactional
    public int deleteCategoryByIds(Long[] ids) {
        int count = 0;
        for (Long id : ids) {
            categoryMapper.deleteCategoryById(id);
            count++;
        }
        return count;
    }

    @Override
    public List<MediaContent> selectContentList(MediaContent content) {
        return contentMapper.selectContentList(content);
    }

    @Override
    public MediaContent selectContentById(Long id) {
        return contentMapper.selectContentById(id);
    }

    @Override
    @Transactional
    public void insertContent(MediaContent content) {
        if (content.getStatus() == null) {
            content.setStatus("0");
        }
        if (content.getViewCount() == null) {
            content.setViewCount(0);
        }
        if (content.getFavoriteCount() == null) {
            content.setFavoriteCount(0);
        }
        contentMapper.insertContent(content);
    }

    @Override
    @Transactional
    public void updateContent(MediaContent content) {
        contentMapper.updateContent(content);
    }

    @Override
    @Transactional
    public void deleteContentById(Long id) {
        contentMapper.deleteContentById(id);
    }

    @Override
    @Transactional
    public int deleteContentByIds(Long[] ids) {
        int count = 0;
        for (Long id : ids) {
            contentMapper.deleteContentById(id);
            count++;
        }
        return count;
    }

    @Override
    public List<MediaContent> selectContentByCategoryId(Long categoryId) {
        return contentMapper.selectContentByCategoryId(categoryId);
    }

    @Override
    @Transactional
    public void incrementViewCount(Long id) {
        contentMapper.incrementViewCount(id);
    }

    @Override
    @Transactional
    public void incrementFavoriteCount(Long id) {
        contentMapper.incrementFavoriteCount(id);
    }
}
