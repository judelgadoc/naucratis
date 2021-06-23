package com.naucratis.naucratis.service;

import com.naucratis.naucratis.model.library.Site;
import com.naucratis.naucratis.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteService
{
    private final SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public Site save(Site site)
    {
        return siteRepository.save(site);
    }

    public Site findById(String id)
    {
        return siteRepository.findById(id).get();
    }

    public void deleteById(String id)
    {
        siteRepository.deleteById(id);
    }
}
