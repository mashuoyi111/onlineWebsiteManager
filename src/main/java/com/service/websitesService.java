package com.service;

import com.domain.Website;

import java.util.List;

/**
 * Created by Ma on 17/3/10.
 */
public interface websitesService {
    public List<Website> getAllWebsites();

    public void insertWebsite(Website w);

    public List<Website> getWebsitesByTagid(Integer tag_id);


    public void deleteWebsite(Integer web_id);

    public Website getWebsiteById(Integer web_id);

    public void updateWebsite(Website w);

    public void setWebsiteFav(Integer web_id);

    public List<Website> searchWebsitesByName(String user_name,String web_name);
}
