package com.service;

import com.domain.Tag;
import com.domain.User;

import java.util.List;

/**
 * Created by Ma on 17/3/10.
 */
public interface tagsService {

    public List<Tag> getTagsByUser(User user);

    public void insertTag(Tag t);

    public void deleteTag(Integer tag_id);

    public Tag getTagById(Integer tag_id);

    public void updateTag(Tag t);
}
